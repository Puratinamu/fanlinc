import redirectManager from '../redirectManager'

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
            const response = await axios.get("/api/v1/getFandoms", { data: {} });
            return response;
        } catch (error) {
            console.error(error)
            return {};
        }
    },
    createFandom: async function (inputName, inputDescription, oidUser, sessionToken) {
        try {
            const response = await axios.post("/api/v1/addFandom", {
                name: inputName,
                description: inputDescription,
                creator: parseInt(oidUser)
            }, {
                headers: {
                    jwt: sessionToken
                }
            });
            return response;
        } catch (error) {
            
            if (error.response.status === 500) {
                redirectManager.goTo(`login?redirect=${redirectManager.getCurrentPath()}`)
            }
            return error.response;
        }
    },

    addFandomToUser: async function (oidUser, oidFandom, interestLevel, sessionToken) {
        try {
            const response = await axios.put("/api/v1/updateFandomRelationship", {
                oidUser,
                oidFandom,
                relationship: interestLevel

            }, {
                headers: {
                    jwt: sessionToken
                }
            });
            return response;
        } catch (error) {
            console.log(error.response)
            if (error.response.status === 500) {
                redirectManager.goTo(`login?redirect=${redirectManager.getCurrentPath()}`)
            }
            return {};
        }
    }
};

export default fandomRequests;

