package ca.utoronto.utm.mcs.projectcloudinfantry

import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.PropertySource
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper


@PropertySource(value = "classpath:application-test.yml")
class UserServiceTest extends BaseSpecification {

    @Autowired
    private MockMvc mvc

    private ObjectMapper objectMapper = new ObjectMapper()

    @Autowired
    private UserRepository userRepository

    def 'Register User and Add to Database'() {
        expect:
        // make a POST request to addUser and get back expected json
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addUser')
                .content('{\n' +
                        '\t"email" : "carla.johnson@gmail.com",\n' +
                        '\t"username": "Carla99",\n' +
                        '\t"password": "password",\n' +
                        '\t"description": "second user",\n' +
                        '\t"fandoms": []\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        // to check the JSON response
        Map resultMap = objectMapper.readValue(result.getResponse().getContentAsString(), HashMap)

        // Make sure all elements in post body are included
        resultMap.get("oidUser").toString() != null
        resultMap.get("email").toString() == "carla.johnson@gmail.com"
        resultMap.get("username").toString() == "Carla99"
        resultMap.get("description").toString() == "second user"
        List<Object> fandoms = resultMap.get("fandoms") as List<Object>
        fandoms.toString() == "[]"
    }

    def 'Register User Fail Because User is Already in the Database'() {
        expect:
        // Add the same user twice, second attempt returns 400 error
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addUser')
                .content('{\n' +
                        '\t"email" : "test@gmail.com",\n' +
                        '\t"username": "test",\n' +
                        '\t"password": "password",\n' +
                        '\t"description": "test user",\n' +
                        '\t"fandoms": []\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
        // to check the JSON response
        Map resultMap = objectMapper.readValue(result.getResponse().getContentAsString(), HashMap)

        // Make sure all elements in post body are included
        resultMap.get("oidUser").toString() != null
        resultMap.get("email").toString() == "test@gmail.com"
        resultMap.get("username").toString() == "test"
        resultMap.get("description").toString() == "test user"
        List<Object> fandoms = resultMap.get("fandoms") as List<Object>
        fandoms.toString() == "[]"

        MvcResult result1 = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addUser')
                .content('{\n' +
                        '\t"email" : "test@gmail.com",\n' +
                        '\t"username": "test",\n' +
                        '\t"password": "password",\n' +
                        '\t"description": "test user again",\n' +
                        '\t"fandoms": []\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn()

        // Check that response is empty
        result1.getResponse().getContentAsString().isEmpty();

    }

    def 'Register User Fail Since Fandom Does Not Exist'() {
        expect:
        // make a POST request to addUser and get back expected json
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addUser')
                .content('{\n' +
                        '\t"email" : "carla1.johnson@gmail.com",\n' +
                        '\t"username": "Carla199",\n' +
                        '\t"password": "password",\n' +
                        '\t"description": "second user",\n' +
                        '\t"fandoms": [{"id": 1234, "level": "CASUAL"}]\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn()
        // Check that response is empty
        result.getResponse().getContentAsString().isEmpty();
    }

    def 'Register User With Existing Fandom'() {
        expect:
        // Add Fandom
        MvcResult result1 = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addFandom')
                .content('{\n' +
                        '\t"name" : "WOW",\n' +
                        '\t"description": "World of Warcraft"\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        // to check the JSON response
        Map resultMap1 = objectMapper.readValue(result1.getResponse().getContentAsString(), HashMap)

        // Make sure all elements in post body are included
        resultMap1.get("oidFandom").toString() != null
        resultMap1.get("name").toString() == "WOW"
        resultMap1.get("description").toString() == "World of Warcraft"

        // make a POST request to addUser and get back expected json
        MvcResult result2 = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addUser')
                .content('{\n' +
                        '\t"email" : "wow@gmail.com",\n' +
                        '\t"username": "wow",\n' +
                        '\t"password": "password",\n' +
                        '\t"description": "wow user",\n' +
                        '\t"fandoms": [ {' +
                        '   "id": ' + resultMap1.get("oidFandom").toString()+ ',' +
                        '   "level": "CASUAL"' +
                        '}]\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        // to check the JSON response
        Map resultMap2 = objectMapper.readValue(result2.getResponse().getContentAsString(), HashMap)

        // Make sure all elements in post body are included
        resultMap2.get("oidUser").toString() != null
        resultMap2.get("email").toString() == "wow@gmail.com"
        resultMap2.get("username").toString() == "wow"
        resultMap2.get("description").toString() == "wow user"
        List<Object> fandoms = resultMap2.get("fandoms") as List<Object>
        Long fandomId = fandoms.get(0) as Long
        fandomId == resultMap1.get("oidFandom")

    }


    def 'User Login'() {
        expect:
        // make a POST request to addUser and get back expected json
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/login')
                .content('{\n' +
                        '\t"email" : "carla.johnson@gmail.com",\n' +
                        '\t"password": "password"\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
    }
}