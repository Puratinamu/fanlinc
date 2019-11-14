package ca.utoronto.utm.mcs.projectcloudinfantry

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository

import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.token.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.PropertySource
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper

@PropertySource(value = "classpath:application-test.yml")
class TextPostServiceTest extends BaseSpecification {

    @Autowired
    private MockMvc mvc

    private ObjectMapper objectMapper = new ObjectMapper()

    @Autowired
    private UserRepository userRepository

    @Autowired
    private FandomRepository fandomRepository

    @Autowired
    private TextPostRepository textPostRepository

    @Autowired
    private TokenService tokenService

    def setup() {
        User user = new User()
        user.setOidUser(1234)
        user.setUsername("yos")
        userRepository.save(user)
    }

    def 'Test add text post'() {
        expect:
        MvcResult fandom = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addFandom')
                .header("jwt", tokenService.generateToken(1234, new HashMap<String, Object>()))
                .content('{\n' +
                        '\t"name": "testFandom",\n' +
                        '\t"description": "testDescription",\n' +
                        '\t"creator": 1234\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        Map fandomMap = objectMapper.readValue(fandom.getResponse().getContentAsString(), HashMap)
        Long oidFandom = fandomMap.get("oidFandom")

        MvcResult user = mvc.perform(MockMvcRequestBuilders
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

        Map userMap = objectMapper.readValue(user.getResponse().getContentAsString(), HashMap)
        Long oidUser = userMap.get("oidUser")

        MvcResult textPost = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addTextPost')
                .header("jwt", tokenService.generateToken(1234, new HashMap<String, Object>()))
                .content('{\n' +
                        '\t"oidUser": "' + oidUser.toString() + '",\n' +
                        '\t"oidFandom": "' + oidFandom.toString() + '",\n' +
                        '\t"text": "Hello World!",' +
                        '\t"creator": 1234' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        Map textMap = objectMapper.readValue(textPost.getResponse().getContentAsString(), HashMap)
        Long oidTextPost = textMap.get("oidContent")

        TextContent text1 = textPostRepository.findById(oidTextPost).get()
        text1.getText() == 'Hello World!'
    }

    def 'Test get text post'() {
        expect:
        MvcResult fandom = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addFandom')
                .header("jwt", tokenService.generateToken(1234, new HashMap<String, Object>()))
                .content('{\n' +
                        '\t"name": "Fandom_2",\n' +
                        '\t"description": "fandom 2 description",\n' +
                        '\t"creator": 1234' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        Map fandomMap = objectMapper.readValue(fandom.getResponse().getContentAsString(), HashMap)
        Long oidFandom = fandomMap.get("oidFandom")

        MvcResult user = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addUser')
                .content('{\n' +
                        '\t"email" : "user2@gmail.com",\n' +
                        '\t"username": "test_2",\n' +
                        '\t"password": "password",\n' +
                        '\t"description": "test user",\n' +
                        '\t"fandoms": []\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        Map userMap = objectMapper.readValue(user.getResponse().getContentAsString(), HashMap)
        Long oidUser = userMap.get("oidUser")

        MvcResult textPost = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addTextPost')
                .header("jwt", tokenService.generateToken(1234, new HashMap<String, Object>()))
                .content('{\n' +
                        '\t"oidUser": "' + oidUser.toString() + '",\n' +
                        '\t"oidFandom": "' + oidFandom.toString() + '",\n' +
                        '\t"text": "Hello World!",' +
                        '\t"creator": 1234' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        Map postTextMap = objectMapper.readValue(textPost.getResponse().getContentAsString(), HashMap)
        Long oidContent = postTextMap.get("oidContent")

        MvcResult getResult = mvc.perform(MockMvcRequestBuilders
                .get('/api/v1/getTextPost')
                .header("jwt", tokenService.generateToken(1234, new HashMap<String, Object>()))
                .content('{\n' +
                        '\t"oidTextPost":' + oidContent.toString() +',\n' +
                        '\t"creator": 1234' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        Map getTextMap = objectMapper.readValue(getResult.getResponse().getContentAsString(), HashMap)
        String text = getTextMap.get("text")
        text == 'Hello World!'
    }
}