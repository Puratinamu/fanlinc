const axios = require('axios')


let textPostRequests = {
    putTextPost: async function (requestBody) {
        try {
            const response = await axios.post('/api/v1/addTextPost', requestBody)
            return response
        }
        catch (error) {
            console.error(error);
            return error
        }
    }
}

export default textPostRequests