import Vue from 'vue';
import Vuex from 'vuex';
import chatRoom from './chatRoom';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        chatRoom,
    },
});