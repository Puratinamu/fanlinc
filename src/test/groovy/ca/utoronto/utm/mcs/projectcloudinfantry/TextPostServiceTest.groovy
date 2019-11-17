package ca.utoronto.utm.mcs.projectcloudinfantry

import ca.utoronto.utm.mcs.projectcloudinfantry.datafactory.ContentFactory
import ca.utoronto.utm.mcs.projectcloudinfantry.datafactory.FandomFactory
import ca.utoronto.utm.mcs.projectcloudinfantry.datafactory.PostFactory
import ca.utoronto.utm.mcs.projectcloudinfantry.datafactory.UserFactory
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Post
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.PostRepository
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.TextContentRepository
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
    private PostRepository postRepository

    @Autowired
    private TextContentRepository textContentRepository

    @Autowired
    private TokenService tokenService

    def 'Test add text post'() {
        given:
        User user = UserFactory.createUser("yos", "yos@yos.com")
        user = userRepository.save(user)

        Fandom fandom = FandomFactory.createFandom("testFandom")
        fandom = fandomRepository.save(fandom)

        expect:
        MvcResult textPost = mvc.perform(MockMvcRequestBuilders
                .post('/api/v1/addTextPost')
                .header("jwt", tokenService.generateToken(user.getOidUser(), new HashMap<String, Object>()))
                .content('{\n' +
                        '\t"oidFandom": "' + fandom.getOidFandom().toString() + '",\n' +
                        '\t"text": "Hello World!",' +
                        '\t"oidCreator": "' + user.getOidUser().toString() + '",\n' +
                        '}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        Map textMap = objectMapper.readValue(textPost.getResponse().getContentAsString(), HashMap)
        Post post = postRepository.findById(textMap.get('oidPost') as Long).get()

        TextContent text = textContentRepository.findById(post.getContent().getOidContent()).get()
        text.getText() == 'Hello World!'

        textMap.get('oidCreator') == user.getOidUser().toString()
        textMap.get('username') == user.getUsername()
        textMap.get('oidFandom') == fandom.getOidFandom().toString()
        textMap.get('fandomName') == fandom.getName()
        textMap.get('text') == (post.getContent() as TextContent).getText()
    }

//    def 'Test get text post'() {
//        given:
//        User user = UserFactory.createUser("yos", "yos@yos.com")
//        user = userRepository.save(user)
//
//        Fandom fandom = FandomFactory.createFandom("testFandom")
//        fandom = fandomRepository.save(fandom)
//
//        TextContent textContent = ContentFactory.createTextContent("testText")
//        textContent = textContentRepository.save(textContent)
//
//        Post post = PostFactory.createPost("testTitle", user, fandom, textContent)
//        post = postRepository.save(post)
//
//        expect:
//        MvcResult getResult = mvc.perform(MockMvcRequestBuilders
//                .get('/api/v1/getTextPost')
//                .header("jwt", tokenService.generateToken(user.getOidUser(), new HashMap<String, Object>()))
//                .content('{\n' +
//                        '\t"oidPost":' + post.getOidPost().toString() +
//                        '}')
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn()
//
//        Map getTextMap = objectMapper.readValue(getResult.getResponse().getContentAsString(), HashMap)
//        getTextMap.get("text") == 'testText'
//    }

    def 'Test get post feed'() {
        given:

        User user1 = UserFactory.createUser("testUser1", "test1@user.com")
        user1 = userRepository.save(user1)

        User user2 = UserFactory.createUser("testUser2", "test2@user.com")
        user2 = userRepository.save(user2)

        User user3 = UserFactory.createUser("testUser3", "test1@user.com")
        user3 = userRepository.save(user3)

        Fandom fandom1 = FandomFactory.createFandom("testFandom1")
        fandom1 = fandomRepository.save(fandom1)

        Fandom fandom2 = FandomFactory.createFandom("testFandom2")
        fandom2 = fandomRepository.save(fandom2)

        TextContent textContent1 = ContentFactory.createTextContent("testText1")
        textContent1 = textContentRepository.save(textContent1)

        TextContent textContent2 = ContentFactory.createTextContent("testText2")
        textContent2 = textContentRepository.save(textContent2)

        Post post1 = PostFactory.createPost("testPost1", user1, fandom1, textContent1)
        post1 = postRepository.save(post1)

        Post post2 = PostFactory.createPost("testPost2", user2, fandom2, textContent2)
        post2 = postRepository.save(post2)

        expect:

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get('/api/v1/getPostFeed?oidCreator=' + user3.getOidUser())
                .header("jwt", tokenService.generateToken(user3.getOidUser(), new HashMap<String, Object>()))
                .content('{}')
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()

        Map resultMap = objectMapper.readValue(result.getResponse().getContentAsString(), HashMap)
        List<Object> posts = resultMap.get("posts") as List<Object>
        Map<String, Object> responsePost1 = posts.get(0) as Map<String, Object>

        responsePost1.get("oidPost") == post1.getOidPost()
        responsePost1.get('oidCreator') == user1.getOidUser().toString()
        responsePost1.get('username') == user1.getUsername()
        responsePost1.get('oidFandom') == fandom1.getOidFandom().toString()
        responsePost1.get('fandomName') == fandom1.getName()
        responsePost1.get('text') == (post1.getContent() as TextContent).getText()

        Map<String, Object> responsePost2 = posts.get(1) as Map<String, Object>
        responsePost2.get("oidPost") == post2.getOidPost()
        responsePost2.get('oidCreator') == user2.getOidUser().toString()
        responsePost2.get('username') == user2.getUsername()
        responsePost2.get('oidFandom') == fandom2.getOidFandom().toString()
        responsePost2.get('fandomName') == fandom2.getName()
        responsePost2.get('text') == (post2.getContent() as TextContent).getText()
    }
}