package ca.utoronto.utm.mcs.projectcloudinfantry

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
@AutoConfigureMockMvc
@PropertySource(value = "classpath:application-test.yml")
class CloudInfantrySpec extends BaseSpecification {

    @Autowired
    private MockMvc mvc

    private ObjectMapper objectMapper = new ObjectMapper()

    @Autowired
    private UserRepository userRepository

    def 'Health test'() {
        expect:
        // make a GET request to /api/health
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get('/api/health')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        // to check the JSON response
        Map resultMap = objectMapper.readValue(result.getResponse().getContentAsString(), HashMap)
        // you can now access the JSON as a map

        // you can also access the repository to check on the database
        userRepository.count() >= 0
    }

}
