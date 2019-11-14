
const axios = require('axios')

let chatRequests = {
    async getChatMessagesForFandom(fandomId, fandomInterestLevel){
        try {
            const response = await axios.get( `/api/v1/messenger/fandom?fandomId=${fandomId}&fandomInterestLevel=${fandomInterestLevel}`)
           
            return response
        }
        catch (error) {
            return error.response
        }
    }
    
}
export default chatRequests;
