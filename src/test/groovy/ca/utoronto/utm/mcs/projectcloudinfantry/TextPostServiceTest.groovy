package ca.utoronto.utm.mcs.projectcloudinfantry

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.TextPostRepository
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

    def 'Test add text post'() {
        expect:
        MvcResult fandom = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addFandom')
                .content('{\n' +
                        '\t"name": "Fandom_1",\n' +
                        '\t"description": "fandom 1 description"\n' +
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
                .content('{\n' +
                        '\t"oidUser": "' + oidUser.toString() + '",\n' +
                        '\t"oidFandom": "' + oidFandom.toString() + '",\n' +
                        '\t"text": "Hello World!"' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        Map textMap = objectMapper.readValue(textPost.getResponse().getContentAsString(), HashMap)
        Long oidTextPost = textMap.get("oidContent")

        TextContent text1 = textPostRepository.findById(oidTextPost).get()
        text1.getText() == 'Hello World!'
    }

}