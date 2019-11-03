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
            return {};
        }
    },

    addFandomToUser: async function (oidUser, oidFandom, interestLevel) {
        try {
            const response = await axios.put("/api/v1/updateFandomRelationship", {
                oidUser,
                oidFandom,
                relationship: interestLevel
            });
            return response;
        } catch (error) {
            console.error(error)
            return {};
        }
    }
};

export default fandomRequests;

