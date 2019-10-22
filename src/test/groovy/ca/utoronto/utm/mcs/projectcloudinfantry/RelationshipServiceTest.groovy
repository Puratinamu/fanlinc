import ca.utoronto.utm.mcs.projectcloudinfantry.BaseSpecification
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToFandom
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserToFandomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.PropertySource
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Shared

@PropertySource(value = "classpath:application-test.yml")
class RelationshipServiceTest extends BaseSpecification {

    @Autowired
    private MockMvc mvc

    private ObjectMapper objectMapper = new ObjectMapper()

    @Autowired
    private UserToFandomRepository userToFandomRepository

    @Autowired
    @Shared
    private FandomRepository fandomRepository

    @Autowired
    @Shared
    private UserRepository userRepository

    @Shared
    User testUser

    @Shared
    Fandom testFandom

    def setupSpec() {
        // Create new User and new fandom and add them to repo
        testUser = new User()
        testUser.setUsername("tberg")
        testUser.setEmail("tanner@email.com")
        testUser.setDescription("I like books")

        testFandom = new Fandom()
        testFandom.setName("Book Fandom")
        testFandom.setDescription("Fandom for people who like books")

        // Add to db
        testUser = userRepository.save(testUser)
        testFandom = fandomRepository.save(testFandom)
    }

    def 'Add fresh relationship from existing user to fandom'() {
        expect:
        // make a PUT request to updateFandomRelationship and get back expected json
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .put('/api/v1/updateFandomRelationship')
                .content('{\n' +
                        '\t"oidUser" : ' + testUser.getOidUser().toString() + ',\n' +
                        '\t"oidFandom": ' + testFandom.getOidFandom().toString() + ',\n' +
                        '\t"relationship": "CASUAL" \n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        // This request doesn't have a return body so we need to query the db ourselves
        UserToFandom relationship = userToFandomRepository.findByUserAndFandomNames(testUser.getUsername(), testFandom.getName())

        relationship != null

    }

    def 'Update existing relationship'() {
        expect:
        // make a PUT request to updateFandomRelationship and get back expected json
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .put('/api/v1/updateFandomRelationship')
                .content('{\n' +
                        '\t"oidUser" : ' + testUser.getOidUser().toString() + ',\n' +
                        '\t"oidFandom": ' + testFandom.getOidFandom().toString() + ',\n' +
                        '\t"relationship": "EXPERT" \n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        // This request doesn't have a return body so we need to query the db ourselves
        UserToFandom relationship = userToFandomRepository.findByUserAndFandomNames(testUser.getUsername(), testFandom.getName())
        assert relationship != null

        relationship.getRelationship() == "EXPERT"

    }

}
