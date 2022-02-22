import { nicknameService } from '@/api/index.js';

async function getNewNickname(callback, errorCallback){
    await nicknameService
        .get('/', {params : { format : 'json', count : 1 }})
        .then((res) => {
            callback(res);
        })
        .catch((err) => {
            errorCallback(err);
        });
}

export {
    getNewNickname
}