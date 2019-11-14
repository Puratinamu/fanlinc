package ca.utoronto.utm.mcs.projectcloudinfantry

import ca.utoronto.utm.mcs.projectcloudinfantry.BaseSpecification
import ca.utoronto.utm.mcs.projectcloudinfantry.FandomFactory
import ca.utoronto.utm.mcs.projectcloudinfantry.UserFactory
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToFandom
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserToFandomRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.token.TokenService
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
class UserToFandomServiceTest extends BaseSpecification {

    @Autowired
    private MockMvc mvc

    private ObjectMapper objectMapper = new ObjectMapper()

    @Autowired
    private UserToFandomRepository userToFandomRepository

    @Autowired
    private FandomRepository fandomRepository

    @Autowired
    private UserRepository userRepository

    @Autowired
    private TokenService tokenService

    @Shared
    User testUser

    @Shared
    Fandom testFandom

    def 'Add fresh relationship from existing user to fandom'() {
        testUser = UserFactory.CreateUser("tannerbeez", "tanner@email.com")
        testFandom = FandomFactory.CreateFandom("Minecraft")
        testUser = userRepository.save(testUser);
        testFandom = fandomRepository.save(testFandom);
        //UserToFandom rel = new UserToFandom(testUser, testFandom, "CASUAL")
        //rel = userToFandomRepository.save(rel)
        System.out.println(testUser.getOidUser())
        System.out.println(testFandom.getOidFandom())

        expect:
        // make a PUT request to updateFandomRelationship and get back expected json
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .put('/api/v1/updateFandomRelationship')
                .header("jwt", tokenService.generateToken(testUser.getOidUser(), new HashMap<String, Object>()))
                .content('{\n' +
                        '\t"oidUser" : ' + testUser.getOidUser().toString() + ',\n' +
                        '\t"oidFandom": ' + testFandom.getOidFandom().toString() + ',\n' +
                        '\t"relationship": "CASUAL" \n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        // This request doesn't have a return body so we need to query the db ourselves
        // UserToFandom relationship = userToFandomRepository.findByUserIDAndFandomID (testUser.getOidUser(), testFandom.getOidFandom())
        // relationship != null

    }

    def 'Update existing relationship'() {
        testUser = UserFactory.CreateUser("tannerbeez", "tanner@email.com")
        testFandom = FandomFactory.CreateFandom("Minecraft")
        testUser = userRepository.save(testUser);
        testFandom = fandomRepository.save(testFandom);
        UserToFandom rel = new UserToFandom(testUser, testFandom, "CASUAL")
        rel = userToFandomRepository.save(rel)

        expect:
        // make a PUT request to updateFandomRelationship and get back expected json
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .put('/api/v1/updateFandomRelationship')
                .header("jwt", tokenService.generateToken(testUser.getOidUser(), new HashMap<String, Object>()))
                .content('{\n' +
                        '\t"oidUser" : ' + testUser.getOidUser().toString() + ',\n' +
                        '\t"oidFandom": ' + testFandom.getOidFandom().toString() + ',\n' +
                        '\t"relationship": "EXPERT" \n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        // This request doesn't have a return body so we need to query the db ourselves
        Optional<UserToFandom> relationship = userToFandomRepository.findById(rel.getOidUserToFandom())
        relationship.isPresent()

        // Check for the changed property
        relationship.get().getRelationship().equals("EXPERT")
    }

}

