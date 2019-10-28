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
    }

    def 'Register User Fail Since Fandom Does Not Exist'() {
        expect:
        // make a POST request to addUser and get back expected json
        mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addUser')
                .content('{\n' +
                        '\t"email" : "carla1.johnson@gmail.com",\n' +
                        '\t"username": "Carla199",\n' +
                        '\t"password": "password",\n' +
                        '\t"description": "second user",\n' +
                        '\t"fandoms": ["1234"]\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
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