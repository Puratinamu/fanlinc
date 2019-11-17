package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Post;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.PostToFandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.RelationshipLevel;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToFandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.PostNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.PostMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.TextContentMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.*;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.TextPostRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements  PostService {

    private final PostRepository postRepository;
    private final TextContentRepository textContentRepository;
    private final UserRepository userRepository;
    private final FandomRepository fandomRepository;
    private final PostMapper postMapper;
    private final TextContentMapper textContentMapper;
    private final PostToFandomRepository postToFandomRepository;
    private final UserToFandomRepository userToFandomRepository;

    public PostServiceImpl(PostRepository postRepository,
                           TextContentRepository textContentRepository,
                           UserRepository userRepository,
                           FandomRepository fandomRepository,
                           PostMapper postMapper,
                           TextContentMapper textContentMapper,
                           PostToFandomRepository postToFandomRepository,
                           UserToFandomRepository userToFandomRepository) {
        this.postRepository = postRepository;
        this.textContentRepository = textContentRepository;
        this.userRepository = userRepository;
        this.fandomRepository = fandomRepository;
        this.postMapper = postMapper;
        this.textContentMapper = textContentMapper;
        this.postToFandomRepository = postToFandomRepository;
        this.userToFandomRepository = userToFandomRepository;
    }

    @Override
    public Post addTextPost(TextPostRequest textPostRequest) {


        User user = userRepository.findById(textPostRequest.getOidCreator()).orElseThrow(UserNotFoundException::new);
        Fandom fandom = fandomRepository.findById(textPostRequest.getOidFandom()).orElseThrow(FandomNotFoundException::new);

        TextContent textContent = textContentMapper.toTextContent(textPostRequest);
        textContent = textContentRepository.save(textContent);

        Post post = postMapper.toPost(textPostRequest);
        post.setContent(textContent);
        post.setCreator(user);
        post.setFandom(fandom);

        Date date = new Date();
        post.setCreationTimestamp(date);
        post.setLastUpdateTimestamp(date);

        post = postRepository.save(post);
        fandom.getPosts().add(post);

        fandomRepository.save(fandom);

        PostToFandom postToFandom = postToFandomRepository.findByOidPostAndOidFandom(post.getOidPost(), fandom.getOidFandom());
        UserToFandom userToFandom = userToFandomRepository.findByUserIDAndFandomID(user.getOidUser(), fandom.getOidFandom());
        postToFandom.setRelationshipLevel(RelationshipLevel.valueOf(userToFandom.getRelationship()));
        postToFandom.setFandom(fandom);
        postToFandom.setPost(post);

        postToFandomRepository.save(postToFandom);

        return post;
    }

    @Override
    public Post getPost(Long oidPost) {
        return this.postRepository.findById(oidPost).orElseThrow(PostNotFoundException::new);
    }

    @Override
    public List<Post> getPostFeed(Long oidUser) {
        User user = userRepository.findById(oidUser).orElseThrow(UserNotFoundException::new);
        List<Long> oidPosts = postRepository.getOidPostFeedByOidUser(user.getOidUser());
        List<Post> posts = new ArrayList<>();
        for (Post post : postRepository.findAllById(oidPosts)) {
            Long oidFandom = postToFandomRepository.findOidFandomByOidPost(post.getOidPost());
            post.setFandom(fandomRepository.findById(oidFandom).orElseThrow(FandomNotFoundException::new));
            posts.add(post);
        }
        return posts;
    }

}
