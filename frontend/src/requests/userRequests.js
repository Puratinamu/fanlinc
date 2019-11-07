// {
//     "oidUser": null,
//     "email": "",
//     "username": "",
//     "description": "",
//     "password":"......"
//     "fandoms": [
//         ...
//     ]
// }

const axios = require('axios')

let userRequests = {

    putUser: async function (requestBody) {
        try {
            const response = await axios.post('/api/v1/addUser', requestBody);
            return response;
        }
        catch (error) {
            console.error(error);
            return error;
        }
    },

    loginUserRequest: async function (email, password) {
        try {
            const response = await axios.post('/api/v1/login', { "email": email, "password": password });
            return response;
        }
        catch (error) {
            console.error(error);
            return error.response;
        }
    },

    getUser: async function (oidUser) {
        try {
            const response = await axios.get('/api/v1/getProfile', {
                params: {
                    oidUser: oidUser
                },
                data: {}
            });
            return response;
        }
        catch (error) {
            console.error(error);
            return error.response;
        }
    }

}

export default userRequests
