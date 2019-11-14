
const axios = require('axios')

let chatRequests = {
    async getChatMessagesForFandom(fandomId, fandomInterestLevel) {
        try {
            const response = await axios.get(`/api/v1/messenger/fandom?fandomId=${fandomId}&fandomInterestLevel=${fandomInterestLevel}`)

            return response
        }
        catch (error) {
            return error.response
        }
    },
    async postMessageToFandom(fandomId, fandomInterestLevel, fromId, content) {
        try {
            const response = await axios.post(`/api/v1/messenger/fandom?from=${fromId}&fandomId=${fandomId}&fandomInterestLevel=${fandomInterestLevel}`, {
                message: content
              }
            )

            return response
        }
        catch (error) {
            return error.response
        }
    }

}
export default chatRequests;
