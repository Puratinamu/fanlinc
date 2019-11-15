import ca.utoronto.utm.mcs.projectcloudinfantry.BaseSpecification
import ca.utoronto.utm.mcs.projectcloudinfantry.datafactory.FandomFactory
import ca.utoronto.utm.mcs.projectcloudinfantry.datafactory.UserFactory
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToFandom
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserToFandomRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.response.UserFandomAndRelationshipInfo
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
class GetProfileTests extends BaseSpecification {

    @Autowired
    private MockMvc mvc

    private ObjectMapper objectMapper = new ObjectMapper()

    @Autowired
    private FandomRepository fandomRepository

    @Autowired
    private UserRepository userRepository

    @Autowired
    private UserToFandomRepository userToFandomRepository;

    @Shared
    User testUser

    @Shared
    Fandom testFandom

    def setupSpec() {
        // Create new User and new fandom and add them to repo
        testUser = UserFactory.CreateUser("Tanner", "tanner@email.com");
        testFandom = FandomFactory.createFandom("Minecraft");

    }

    def 'Get profile of not existent user'() {
        expect:
        // make a GET to get profile and expect a 404 Not Found
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get('/api/v1/getProfile')
                .content('{"oidUser" : -1 }')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn()
    }

    def 'Get profile with bad request body'() {
        expect:
        // make a GET to get profile and expect a 400 Bad Request
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get('/api/v1/getProfile')
                .content('{"wrongField" : 3 }')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn()
    }

    def 'Get profile with existing user and no fandoms'() {
        // Add to db
        testUser = userRepository.save(testUser)
        // testFandom = fandomRepository.save(testFandom)

        // Create a relationship
        // UserToFandom rel = new UserToFandom(testUser, testFandom, "CASUAL");
        // userToFandomRepository.save(rel);
        expect:
        // make a GET to get profile and expect a 400 Bad Request
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get('/api/v1/getProfile')
                .content('{"oidUser" : ' + testUser.getOidUser() + ' }')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        // to check the JSON response
        Map resultMap = objectMapper.readValue(result.getResponse().getContentAsString(), HashMap)

        // Make sure all elements in post body are included
        resultMap.get("oidUser").toString() != null
        resultMap.get("username").toString() == "Tanner"
        resultMap.get("description").toString() == "I am a user"
        List<Object> fandoms = resultMap.get("fandoms") as List<Object>
        fandoms.toString() == "[]"
    }

    def 'Get profile with existing user with multiple fandoms'() {
        // Add to db
        testUser = userRepository.save(testUser)

        testFandom = fandomRepository.save(testFandom)
        UserToFandom rel = new UserToFandom(testUser, testFandom, "CASUAL");
        userToFandomRepository.save(rel);

        testFandom = FandomFactory.createFandom("LOL");
        testFandom = fandomRepository.save(testFandom)
        rel = new UserToFandom(testUser, testFandom, "EXPERT");
        userToFandomRepository.save(rel);

        expect:
        // make a GET to get profile and expect a 400 Bad Request
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get('/api/v1/getProfile')
                .content('{"oidUser" : ' + testUser.getOidUser() + ' }')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        // to check the JSON response
        Map resultMap = objectMapper.readValue(result.getResponse().getContentAsString(), HashMap)

        // Make sure all elements in post body are included
        resultMap.get("oidUser").toString() != null
        resultMap.get("username").toString() == "Tanner"
        resultMap.get("description").toString() == "I am a user"
        List<Object> fandoms = resultMap.get("fandoms") as List<Object>
        UserFandomAndRelationshipInfo data = fandoms.get(1) as UserFandomAndRelationshipInfo
        data.getName() == "LOL" || data.getName() == "Minecraft"
    }
}

