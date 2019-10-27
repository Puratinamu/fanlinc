const axios = require('axios');

const LEVELS = {
    1: "LIMITED",
    2: "CASUAL",
    3: "VERY_INVOLVED",
    4: "EXPERT"
};

let fandomRequests = {
    getLevels: () => LEVELS,
    getAllFandoms: async function () {
        try {
            const response = await axios.get("/api/v1/getFandoms", {data: {}});
            return response;
        } catch (error) {
            console.error(error)
            return {}
        }
    }
};

export default fandomRequests
