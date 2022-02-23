const state = {
    roomId: "",
};

const getters = {
    roomId(state) {
        return state.roomId;
    }
};

const mutations = {
    SET_ROOM_ID(state, payload){
        console.log("test");
        state.roomId = payload;
    }
};

const actions = {
    set_roomId(context, data) {
        console.log("sad");
        context.commit('SET_ROOM_ID',data);
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