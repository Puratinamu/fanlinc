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
    },
    loginUserRequest: async function (email, password) {
        try {
            const response = await axios.post('/api/v1/login', { "email": email, "password": password })
            return response
        }
        catch (error) {
            return error.response
        }
    },

    getContacts: async function (oidUser) {
        try {
            return await axios.put("/api/v1/getContacts", {
                oidUser,
            });
        } catch (error) {
            console.error(error)
            return {};
        }
    }

};
export default userRequests