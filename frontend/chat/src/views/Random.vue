<template>
  <div>테스트 페이지</div>
</template>

<script>
import { mapActions } from 'vuex';
import { getRandomRoom } from '@/api/chatRooms.js'
export default {
    created(){
        getRandomRoom(
            (res) => {
                console.log(res);
                this.set_roomId(res.data);
                this.$router.push( {name:'Room'});
            },
            (err) => {
                console.log(err);
                this.$router.push({name:'Main'});
            }
        )
    },
    methods: {
        ...mapActions(['set_roomId']),
    }
}
</script>

<style>

</style>