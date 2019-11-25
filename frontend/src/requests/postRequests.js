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
    putPost: async function (requestBody, sessionToken) {
        try {
            const response = await axios.post('/api/v1/addTextPost', requestBody 
            , {
                headers: {
                    jwt: sessionToken
                }
            }
            )
            return response
        }
        catch (error) {
            console.error(error);
            return error
        }
    },

};

export default postRequests;
