<template>
  <div>
      <div><h1>채팅방 목록</h1></div>
      <div
      v-for="(item, idx) in roomList"
      :key="idx"
      >
      <a @click.prevent="move(item)">{{item.name}}</a>
      </div>
      <div><input v-model="newRoomName"/> <button @click="createRoom()">채팅방 생성</button></div>
  </div>
</template>

<script>
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
      createChatRoom(
        this.newRoomName,
        () => {
          this.getRooms();
        },
        (err) => {
          console.log(err);
        }
      )
    },
    move(item){
      this.$router.push( {name:'Room' , params : { roomId : item.roomId } });
    }
  }
}
</script>

<style>

</style>