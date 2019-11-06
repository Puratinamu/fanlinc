import cookieManager from '../cookieManager'

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

            let jwt = response.data.jwt;
            let oidUser = response.data.oidUser;
            cookieManager.setCookie("sessionToken",jwt, 1)
            cookieManager.setCookie("authenticatedUserEmail", email, 1)
            cookieManager.setCookie("authenticatedOidUser", oidUser, 1)
            return response
        }
        catch (error) {
            return error.response
        }
    }
}
export default userRequests