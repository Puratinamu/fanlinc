import redirectManager from '../redirectManager'

const axios = require('axios');

let postRequests = {
    getPostFeed: async function (oidUser, sessionToken) {
        try {
            const response = await axios.get(`/api/v1/getPostFeed?oidUser=${parseInt(oidUser)}`,  { data:{}, 
                headers: {
                    jwt: sessionToken,
                }
            }
            );
            return response;
        } catch (error) {
            console.error(error)
            return {};
        }
    },

};

export default postRequests;
