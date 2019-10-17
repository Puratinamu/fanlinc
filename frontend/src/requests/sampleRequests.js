const axios = require('axios')

async function sampleRequest(){
    try {
        const response = await axios.get('/user?ID=12345');
        console.log(response);
        return response;

        } catch (error) {
        console.error(error);
    }
}