import axios from "axios";

function createAxiosService(){
    return axios.create(
        {
            baseURL: 'http://10.10.1.85:8080',
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