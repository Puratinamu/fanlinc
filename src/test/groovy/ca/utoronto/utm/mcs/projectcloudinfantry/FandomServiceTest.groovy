package ca.utoronto.utm.mcs.projectcloudinfantry

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.token.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.PropertySource
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.context.WebApplicationContext
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper


@PropertySource(value = "classpath:application-test.yml")
class FandomServiceTest extends BaseSpecification {

    @Autowired
    private MockMvc mvc

    private ObjectMapper objectMapper = new ObjectMapper()

    @Autowired
    private UserRepository userRepository

    @Autowired
    private FandomRepository fandomRepository

    @Autowired
    private TokenService tokenService

    def setup() {
        User user = new User()
        user.setOidUser(1234)
        user.setUsername("yos")
        userRepository.save(user)
    }

    def 'Test add fandom'() {
        expect:
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addFandom')
                .header("jwt", tokenService.generateToken(1234, new HashMap<String, Object>()))
                .content('{\n' +
                        '\t"name": "testAddFandom",\n' +
                        '\t"description": "testDescription",\n' +
                        '\t"creator": 1234\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        Map resultMap = objectMapper.readValue(result.getResponse().getContentAsString(), HashMap)
        Long oidFandom = resultMap.get("oidFandom")
        Fandom fandom = fandomRepository.findById(oidFandom).get()
        fandom.getName() == 'testAddFandom'
        fandom.getDescription() == 'testDescription'
    }

    def 'Test get fandom'() {
        expect:
        MvcResult postResult = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addFandom')
                .header("jwt", tokenService.generateToken(1234, new HashMap<String, Object>()))
                .content('{\n' +
                        '\t"name": "Fandom_1",\n' +
                        '\t"description": "fandom 1 descr",\n' +
                        '\t"creator": 1234' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        Map postMap = objectMapper.readValue(postResult.getResponse().getContentAsString(), HashMap)
        Long oidFandom = postMap.get("oidFandom")

        MvcResult getResult = mvc.perform(MockMvcRequestBuilders
                .get('/api/v1/getFandom')
                .content('{\n' +
                        '\t"oidFandom":' + oidFandom.toString() +'\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        Map getMap = objectMapper.readValue(getResult.getResponse().getContentAsString(), HashMap)
        String name = getMap.get("name")
        String description = getMap.get("description")
        name == "Fandom_1"
        description == "fandom 1 descr"
    }

    def 'Test get fandom by name'() {
        expect:
        mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addFandom')
                .header("jwt", tokenService.generateToken(1234, new HashMap<String, Object>()))
                .content('{\n' +
                        '\t"name": "Fandom_2",\n' +
                        '\t"description": "fandom 2 descr",\n' +
                        '\t"creator": 1234' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())

        MvcResult getResult = mvc.perform(MockMvcRequestBuilders
                .get('/api/v1/getFandomByName')
                .content('{\n' +
                        '\t"name": "Fandom_2"\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        Map getMap = objectMapper.readValue(getResult.getResponse().getContentAsString(), HashMap)
        String name = getMap.get("name")
        String description = getMap.get("description")
        name == "Fandom_2"
        description == "fandom 2 descr"
    }

    def 'Test add fandom 422'() {
        expect:
        MvcResult postResult_1 = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addFandom')
                .header("jwt", tokenService.generateToken(1234, new HashMap<String, Object>()))
                .content('{\n' +
                        '\t"name": "Fandom_3",\n' +
                        '\t"description": "fandom 3 descr",\n' +
                        '\t"creator": 1234\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        MvcResult postResult_2 = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addFandom')
                .header("jwt", tokenService.generateToken(1234, new HashMap<String, Object>()))
                .content('{\n' +
                        '\t"name": "Fandom_3",\n' +
                        '\t"description": "this should not be added to db",\n' +
                        '\t"creator": 1234\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andReturn()

        Map result_1Map = objectMapper.readValue(postResult_1.getResponse().getContentAsString(), HashMap)
        Long oidFandom = result_1Map.get("oidFandom")

        result_1Map.get("name").toString() == "Fandom_3"
        String description = result_1Map.get("description").toString() == "fandom 3 descr"

        Fandom fandom = fandomRepository.findById(oidFandom).get()
        fandom.getName() == "Fandom_3"
        fandom.getDescription() == "fandom 3 descr"

    }

    def 'Test get fandom 404'() {
        expect:
        mvc.perform(MockMvcRequestBuilders
                .get('/api/v1/getFandom')
                .content('{\n' +
                        '\t"oidFandom": "9999999999999"\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
    }

    def 'Test get fandom by name 404'() {
        expect:
        mvc.perform(MockMvcRequestBuilders
                .get('/api/v1/getFandom')
                .content('{\n' +
                        '\t"oidFandom": "9999999999999"\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
    }

}
