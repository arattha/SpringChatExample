<template>
  <div>
        <div id="textarea">
            <div v-bind:class=" { active: writer == message.writer } " v-for="(message,index) in messageList" :key="index" >
                <div class="notice" v-if =" message.writer === '' "> {{message.message}}</div>
                <div v-else >{{message.writer}} : {{message.message}}</div>
            </div>
        </div>
        <div><input v-model="message" @keyup.enter="sendMessage()"> <button @click="sendMessage()" >메세지 보내기</button></div> 
  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
//import {getNewNickname} from '@/api/nickname.js'
import {mapGetters} from 'vuex';

export default {
    data(){
        return {
            writer : "최",
            message : "",
            messageList : [],
        }
    },
    computed:{
        ...mapGetters(['roomId','nickname'])
    },
    async created(){
        this.writer = this.nickname
        // await getNewNickname(
        //     (res) => {
        //         this.writer= res.data.words[0]
        //     },
        //     () => {
        //         alert("에러")
        //         this.$router.push( {name:'Main' });
        //     }
        // )
        this.connect();
        console.log(this.roomId);
    },
    methods : {
        sendMessage(){
            if (this.stompClient && this.stompClient.connected) {
                    const msg = { 
                        roomId : this.roomId,
                        writer: this.writer,
                        message: this.message 
                    };
                this.stompClient.send("/pub/chat/message", JSON.stringify(msg), {});
            }
            this.message = '';
        },
        connect() {
            const serverURL = "http://10.10.25.236:8082/socket"
            let socket = new SockJS(serverURL);
            this.stompClient = Stomp.over(socket);
            console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
            this.stompClient.connect(
                {},
                frame => {
                // 소켓 연결 성공
                    this.connected = true;
                    console.log('소켓 연결 성공', frame);
                    // 서버의 메시지 전송 endpoint를 구독합니다.
                    // 이런형태를 pub sub 구조라고 합니다.
                    this.stompClient.send(
                        '/pub/chat/enter',
                        JSON.stringify({
                            roomId : this.roomId,
                            writer : this.writer,
                            message : null,
                        }) ,
                        {}
                    );
                    this.stompClient.subscribe("/sub/chat/room/"+this.roomId, 
                        res => {
                            //console.log('구독으로 받은 메시지 입니다.', res.body);

                            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                            this.messageList.push(JSON.parse(res.body))
                        }
                    );
                },
                error => {
                // 소켓 연결 실패
                console.log('소켓 연결 실패', error);
                this.connected = false;
                }
            );
        }
    },
    beforeDestroy(){
        console.log("파괴전" + this.roomId)
        this.stompClient.unsubscribe("/sub/chat/room/"+this.roomId);
    }
}
</script>

<style>
    #textarea {
        height: 50vh;
        width: 75vh;
        border: 1px solid black;
        overflow-y:auto;
        display: flex;
        flex-direction: column;
        /* align-items: flex-end; */
    }
    .active{
        width: 100%;
        display: flex;
        justify-content: flex-end;
    }
    .notice{
        background-color: aqua;
        text-align: center;
    }
</style>