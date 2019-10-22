// {
//     "oidUser": null,
//     "email": "",
//     "username": "",
//     "description": "",
//     "password":"......"
//     "fandoms": [
//         ""
//     ]
// }

const axios = require('axios')

let userRequests = {
    putUser: async function (requestBody) {
        try {
            const response = await axios.post('/api/v1/addUser', requestBody)
            return response
        }
        catch (error) {
            console.error(error);
            return error
        }
    }
}
export default userRequests