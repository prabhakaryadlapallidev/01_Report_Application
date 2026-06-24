import axios from "axios"


const BASE_URL="http://localhost:8080";

export const getDropdownData=()=>{
return axios.get(`${BASE_URL}/reports/init`);
};

export const searchRecords = (request) => {
    return axios.post(`${BASE_URL}/reports/search`, request);
};