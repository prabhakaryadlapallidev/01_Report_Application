import axios from "axios"


const BASE_URL="http://localhost:8080";

export const getDropdownData=()=>{
return axios.get(`${BASE_URL}/reports/init`);
};

export const searchRecords = (request) => {
    return axios.post(`${BASE_URL}/reports/search`, request);
};

export const exportExcel = (request) => {
    return axios.post(
        "http://localhost:8080/reports/excel",
        request,
        {
            responseType: "blob"
        }
    );
};

export const exportPdf = (request) => {
    return axios.post(
        "http://localhost:8080/reports/pdf",
        request,
        {
            responseType: "blob"
        }
    );
};