
const axios = require('axios')

let contactRequests = {
    async getContacts(userId, sessionToken) {
        try {
            const response = await axios.get(`/api/v1/getContacts/?oidUser=${userId}`, {
                data: {},
                headers: {
                    jwt: sessionToken,
                }
            }
            )

            return response
        }
        catch (error) {
            return error.response
        }
    }
}
export default contactRequests;