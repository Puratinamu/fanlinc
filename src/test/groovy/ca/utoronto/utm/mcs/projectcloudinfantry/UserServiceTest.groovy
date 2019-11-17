package ca.utoronto.utm.mcs.projectcloudinfantry

import ca.utoronto.utm.mcs.projectcloudinfantry.datafactory.UserFactory
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToContact
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToFandom
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserContactInfoResult
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserToContactRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.security.BcryptUtils
import ca.utoronto.utm.mcs.projectcloudinfantry.token.TokenService
import ca.utoronto.utm.mcs.projectcloudinfantry.token.extractor.TokenExtractor
import io.jsonwebtoken.Claims
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.PropertySource
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper

import java.time.Instant


@PropertySource(value = "classpath:application-test.yml")
class UserServiceTest extends BaseSpecification {

    @Autowired
    private MockMvc mvc

    private ObjectMapper objectMapper = new ObjectMapper()

    @Autowired
    private UserRepository userRepository

    @Autowired
    private FandomRepository fandomRepository

    @Autowired
    private UserToContactRepository userToContactRepository

    @Autowired TokenService tokenService

    @Autowired
    private TokenExtractor tokenExtractor

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
        fandoms.toString() == "[]"
    }

    def 'Register User Fail Because User is Already in the Database'() {
        expect:
        // Add the same user twice, second attempt returns 400 error
        MvcResult result = mvc.perform(MockMvcRequestBuilders
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
        // to check the JSON response
        Map resultMap = objectMapper.readValue(result.getResponse().getContentAsString(), HashMap)

        // Make sure all elements in post body are included
        resultMap.get("oidUser").toString() != null
        resultMap.get("email").toString() == "test@gmail.com"
        resultMap.get("username").toString() == "test"
        resultMap.get("description").toString() == "test user"
        List<Object> fandoms = resultMap.get("fandoms") as List<Object>
        fandoms.toString() == "[]"

        MvcResult result1 = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addUser')
                .content('{\n' +
                        '\t"email" : "test@gmail.com",\n' +
                        '\t"username": "test",\n' +
                        '\t"password": "password",\n' +
                        '\t"description": "test user again",\n' +
                        '\t"fandoms": []\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn()

        // Check that response is empty
        result1.getResponse().getContentAsString().isEmpty();

    }

    def 'Register User Fail Since Fandom Does Not Exist'() {
        expect:
        // make a POST request to addUser and get back expected json
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addUser')
                .content('{\n' +
                        '\t"email" : "carla1.johnson@gmail.com",\n' +
                        '\t"username": "Carla199",\n' +
                        '\t"password": "password",\n' +
                        '\t"description": "second user",\n' +
                        '\t"fandoms": [{"oidFandom": 1234, "level": "CASUAL"}]\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn()
        // Check that response is empty
        result.getResponse().getContentAsString().isEmpty();
    }

    def 'Register User With Existing Fandom'() {
        setup:
        // Add fandom
        Fandom fandom = new Fandom();
        fandom.setName("WOW")
        fandom.setDescription("World of Warcraft")
        fandomRepository.save(fandom)

        expect:

        // make a POST request to addUser and get back expected json
        MvcResult result2 = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addUser')
                .content('{\n' +
                        '\t"email" : "wow@gmail.com",\n' +
                        '\t"username": "wow",\n' +
                        '\t"password": "password",\n' +
                        '\t"description": "wow user",\n' +
                        '\t"fandoms": [ {' +
                        '   "oidFandom": ' + fandom.getOidFandom().toString() + ',' +
                        '   "level": "CASUAL"' +
                        '}]\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        // to check the JSON response
        Map resultMap2 = objectMapper.readValue(result2.getResponse().getContentAsString(), HashMap)

        // Make sure all elements in post body are included
        resultMap2.get("oidUser").toString() != null
        resultMap2.get("email").toString() == "wow@gmail.com"
        resultMap2.get("username").toString() == "wow"
        resultMap2.get("description").toString() == "wow user"
        List<Object> fandoms = resultMap2.get("fandoms") as List<Object>
        Long fandomId = fandoms.get(0) as Long
        fandomId == fandom.getOidFandom()
    }


    def 'User Login'() {
        Date now = Date.from(Instant.now())
        User user = new User()
        user.setEmail("logmein@gmail.com")
        user.setPassword(BcryptUtils.encodePassword("password"))
        User savedUser = userRepository.save(user)

        expect:
        // make a POST request to addUser and get back expected json
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/login')
                .content('{\n' +
                        '\t"email" : "logmein@gmail.com",\n' +
                        '\t"password": "password"\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
        Map resultMap = objectMapper.readValue(result.getResponse().getContentAsString(), HashMap)
        String jwt = resultMap.get("jwt")
        resultMap.get("oidUser") == savedUser.getOidUser()
        Claims claims = tokenExtractor.extractToken(jwt)
        claims.getSubject() as Long == savedUser.getOidUser()
        claims.getExpiration().after(now)
    }


    def 'User Login Fails'() {
        expect:
        // make a POST request to addUser and get back expected json
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/login')
                .content('{\n' +
                        '\t"email" : "carla.johnson@gmail.com",\n' +
                        '\t"password": "wrongbadpassword"\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andReturn()
    }


    def 'Add Contact'() {
        User user = UserFactory.createUser("user1","user1@gmail.com")
        User savedUser = userRepository.save(user)

        User contactUser = UserFactory.createUser("contact1","contact1@gmail.com")
        User savedContactUser = userRepository.save(contactUser)

        expect:
        // make a POST request to addUser and get back expected json
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .put('/api/v1/addContact')
                .header("jwt", tokenService.generateToken(user.getOidUser(), new HashMap<String, Object>()))
                .content('{\n' +
                        '\t"oidUser" : ' + savedUser.getOidUser() +',\n' +
                        '\t"contactOidUser": ' + savedContactUser.getOidUser() + '\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        UserContactInfoResult dbContact = userToContactRepository.findByUserIdAndUserContactId(
                savedUser.getOidUser(), savedContactUser.getOidUser())

        // Verify relationship is in database
        dbContact.getUser().getOidUser() == savedUser.getOidUser()
        dbContact.getContact().getOidUser() == savedContactUser.getOidUser()
    }

    def 'View Contacts'() {
        User user = UserFactory.createUser("currentUser","currentUser@gmail.com")
        User savedUser = userRepository.save(user)

        User contactUser = UserFactory.createUser("viewContact1","viewcontact1@gmail.com")
        User savedContactUser = userRepository.save(contactUser)

        // Add contactUser to user's contact list
        UserToContact addContact = new UserToContact(savedUser, savedContactUser)
        userToContactRepository.save(addContact)

        expect:
        // make a POST request to addUser and get back expected json
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get('/api/v1/getContacts')
                .header("jwt", tokenService.generateToken(user.getOidUser(), new HashMap<String, Object>()))
                .param("oidUser", savedUser.getOidUser() as String))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        // to check the JSON response
        Map resultMap = objectMapper.readValue(result.getResponse().getContentAsString(), HashMap)

        // Make sure all elements in post body are included
        List<Object> contacts = resultMap.get("contacts") as List<Object>
        Object contact = contacts.get(0) as Map<String, Object>
        String email = contact.get("email") as String
        String username = contact.get("username") as String

        email == "viewcontact1@gmail.com"
        username == "viewContact1"
    }
}