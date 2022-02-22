<template>
  <div id="app">
    <div><h1>PayChat</h1></div>
    <router-view />
    <!--
    유저이름: 
    <input
      v-model="userName"
      type="text"
    >
    내용: <input
      v-model="message"
      type="text"
      @keyup="sendMessage"
    >
    <div
      v-for="(item, idx) in recvList"
      :key="idx"
    >
      <h3>유저이름: {{ item.userName }}</h3>
      <h3>내용: {{ item.content }}</h3>
    </div>-->
  </div>
</template>

<script>

export default {
  name: 'App'
}
</script>
