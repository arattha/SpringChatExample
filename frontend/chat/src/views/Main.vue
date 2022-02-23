<template>
  <div>
      <div><h1>채팅방 목록</h1></div>
      <div
      v-for="(item, idx) in roomList"
      :key="idx"
      >
      <a @click.prevent="move(item)">{{item.name}}</a>
      </div>
      <div><input v-model=newRoomName /> <button @click="createRoom()">채팅방 생성</button></div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
import { getChatRooms, createChatRoom } from "@/api/chatRooms.js";

export default {
  data(){
    return {
      newRoomName : "",
      roomList : [],
    }
  },
  created(){
    this.getRooms();
  },
  methods : {
    ...mapActions(['set_roomId']),
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
      this.set_roomId(item.roomId);
      this.$router.push( {name:'Room'});
    }
  }
}
</script>

<style>

</style>