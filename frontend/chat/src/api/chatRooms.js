import { axiosService } from '@/api/index.js';

async function getChatRooms(callback, errorCallback){
    await axiosService
        .get('/rooms')
        .then((res) => {
            callback(res);
        })
        .catch((err) => {
            errorCallback(err);
        });
}

async function createChatRoom(data, callback, errorCallback){
    await axiosService
        .post('/room', JSON.stringify(data))
        .then((res) => {
            callback(res);
        })
        .catch((err) => {
            errorCallback(err);
        });
}

async function getChatRoom(callback, errorCallback){
    await axiosService
        .get('/room')
        .then((res) => {
            callback(res);
        })
        .catch((err) => {
            errorCallback(err);
        });
}

export {
    getChatRooms,
    createChatRoom,
    getChatRoom
}