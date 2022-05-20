<template>
  <div>
      <div><h1>채팅방 목록</h1></div>
      <div
      v-for="(item, idx) in roomList"
      :key="idx"
      >
      <a @click.prevent="move(item)">{{item.name}}</a>
      </div>
      <div>닉네임 설정 : <input v-model=nickname /></div>
      <div><input v-model=newRoomName /> <button @click="createRoom()">채팅방 생성</button></div>
      <div><button @click="random()">랜덤</button></div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
import { getChatRooms, createChatRoom } from "@/api/chatRooms.js";

export default {
  data(){
    return {
      nickname: "",
      newRoomName : "",
      roomList : [],
    }
  },
  created(){
    this.getRooms();
  },
  methods : {
    ...mapActions(['set_roomId','set_nickname']),
    getRooms(){
      getChatRooms(
      (res) => {
        this.roomList = res.data;
      },
      (err) => {
        console.log(err);
      }
    );
    },
    createRoom(){
      let data = {
        roomName : this.newRoomName
      }
      createChatRoom(
        data,
        () => {
          this.getRooms();
        },
        (err) => {
          console.log(err);
        }
      )
    },
    move(item){
      console.log("이동중" , item.roomId);
      this.set_nickname(this.nickname);
      this.set_roomId(item.roomId);
      this.$router.push( {name:'Room'});
    },
    random(){
      this.set_nickname(this.nickname);
      this.$router.push( {name:'Random'});
    }
  }
}
</script>

<style>

</style>