const axios = require('axios')

let fandomRequests = {
    listFandoms: async function (fandomName) {
        let response = {
            "oidFandom": 1234,
            "name": "LOL",
            "description": "league of legends"
        }
        try {
            axios.get("/api/fandoms")
            return response;
        } catch (error) {
            console.error(error)
            return {}
        }
    }

}
export default fandomRequests