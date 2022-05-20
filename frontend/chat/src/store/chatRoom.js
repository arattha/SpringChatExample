const state = {
    roomId: "",
    nickname:"",
};

const getters = {
    roomId(state) {
        return state.roomId;
    },
    nickname(state){
        return state.nickname;
    }
};

const mutations = {
    SET_ROOM_ID(state, payload){
        console.log("test");
        state.roomId = payload;
    },
    SET_NICKNAME(state,payload){
        state.nickname = payload;
    }
};

const actions = {
    set_roomId(context, data) {
        console.log("sad");
        context.commit('SET_ROOM_ID',data);
    },
    set_nickname(context, data){
        context.commit('SET_NICKNAME',data);
    }
};

export default {
    state: {
        ...state,
    },
    getters,
    mutations,
    actions,
};