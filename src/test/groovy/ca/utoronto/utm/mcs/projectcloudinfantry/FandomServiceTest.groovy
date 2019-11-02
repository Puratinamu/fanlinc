package ca.utoronto.utm.mcs.projectcloudinfantry

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository
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
/**
 * EXAMPLE TEST CLASS
 */

@PropertySource(value = "classpath:application-test.yml")
class FandomServiceTest extends BaseSpecification {

    @Autowired
    private MockMvc mvc

    private ObjectMapper objectMapper = new ObjectMapper()

    @Autowired
    private UserRepository userRepository

    @Autowired
    private FandomRepository fandomRepository

    def 'Test add fandom'() {
        expect:
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addFandom')
                .content('{\n' +
                        '\t"name": "testFandom",\n' +
                        '\t"description": "testDescription"\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        Map resultMap = objectMapper.readValue(result.getResponse().getContentAsString(), HashMap)
        Long oidFandom = resultMap.get("oidFandom")
        Fandom fandom = fandomRepository.findById(oidFandom).get()
        fandom.getName() == 'testFandom'
        fandom.getDescription() == 'testDescription'
    }

    def 'Test get fandom'() {
        expect:
        MvcResult postResult = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addFandom')
                .content('{\n' +
                        '\t"name": "Fandom_1",\n' +
                        '\t"description": "fandom 1 descr"\n' +
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
                .content('{\n' +
                        '\t"name": "Fandom_2",\n' +
                        '\t"description": "fandom 2 descr"\n' +
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
                .content('{\n' +
                        '\t"name": "Fandom_3",\n' +
                        '\t"description": "fandom 3 descr"\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        MvcResult postResult_2 = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addFandom')
                .content('{\n' +
                        '\t"name": "Fandom_3",\n' +
                        '\t"description": "this should not be added to db"\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andReturn()

        Map result1Map = objectMapper.readValue(postResult_1.getResponse().getContentAsString(), HashMap)
        Long oidFandom = result1Map.get("oidFandom")

        String name = result1Map.get("name")
        String description = result1Map.get("description")
        name == "Fandom_3"
        description == "fandom 3 descr"

        Fandom fandom = fandomRepository.findById(oidFandom).get()
        fandom.getName() == name
        fandom.getDescription() == description

    }

    def 'Test get fandom 404'() {
        expect:
        mvc.perform(MockMvcRequestBuilders
                .get('/api/v1/getFandom')
                .content('{\n' +
                        '\t"oidFandom": "9999999999999" \n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
    }

    def 'Test get fandom by name 404'() {
        expect:
        mvc.perform(MockMvcRequestBuilders
                .get('/api/v1/getFandom')
                .content('{\n' +
                        '\t"oidFandom": "9999999999999" \n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
    }

}
