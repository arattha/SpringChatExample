import axios from "axios";

function createAxiosService(){
    return axios.create(
        {
            baseURL: 'http://localhost:8080/chat',
            headers: {
                'Content-type' : 'application/json',
            },
        }
    )
}

function createNickService(){
    return axios.create(
        {
            baseURL: 'https://nickname.hwanmoo.kr/',
            headers: {
                'Content-type' : 'application/json',
            },
        }
    )
}

export const axiosService = createAxiosService();
export const nicknameService = createNickService();