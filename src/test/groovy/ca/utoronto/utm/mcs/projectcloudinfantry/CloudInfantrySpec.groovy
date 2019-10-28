package ca.utoronto.utm.mcs.projectcloudinfantry

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.context.annotation.PropertySource
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper
/**
 * EXAMPLE TEST CLASS
 */
@PropertySource(value = "classpath:application-test.yml")
class CloudInfantrySpec extends BaseSpecification {

    @Autowired
    private MockMvc mvc

    private ObjectMapper objectMapper = new ObjectMapper()

    @Autowired
    private UserRepository userRepository

    @Autowired
    private FandomRepository fandomRepository

    def 'Health test'() {
        expect:
        // make a GET request to /api/health
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get('/api/v1/health'))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        // you can also access the repository to check on the database
        userRepository.count() >= 0
    }

    def 'Test add fandom'() {
        expect:
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addFandom')
                .contentType(MediaType.APPLICATION_JSON)
                .content('{"name": "testFandom", "description": "testDescription"}'))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        Map resultMap = objectMapper.readValue(result.getResponse().getContentAsString(), HashMap)
        Long oidFandom = resultMap.get("oidFandom")
        Fandom fandom = fandomRepository.findById(oidFandom).get()
        fandom.getName() == 'testFandom'
        fandom.getDescription() == 'testDescription'
    }

}
