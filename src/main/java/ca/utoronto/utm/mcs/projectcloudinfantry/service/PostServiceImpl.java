package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Post;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.PostNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.PostMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.TextContentMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.PostRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.TextContentRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.TextPostRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements  PostService {

    private final PostRepository postRepository;
    private final TextContentRepository textContentRepository;
    private final UserRepository userRepository;
    private final FandomRepository fandomRepository;
    private final PostMapper postMapper;
    private final TextContentMapper textContentMapper;

    public PostServiceImpl(PostRepository postRepository,
                           TextContentRepository textContentRepository,
                           UserRepository userRepository,
                           FandomRepository fandomRepository,
                           PostMapper postMapper,
                           TextContentMapper textContentMapper) {
        this.postRepository = postRepository;
        this.textContentRepository = textContentRepository;
        this.userRepository = userRepository;
        this.fandomRepository = fandomRepository;
        this.postMapper = postMapper;
        this.textContentMapper = textContentMapper;
    }

    @Override
    public Post addTextPost(TextPostRequest textPostRequest) {
        TextContent textContent = textContentMapper.toTextContent(textPostRequest);
        textContent = textContentRepository.save(textContent);

        Post post = postMapper.toPost(textPostRequest);

        User user = userRepository.findById(textPostRequest.getOidCreator()).orElseThrow(UserNotFoundException::new);
        Fandom fandom = fandomRepository.findById(textPostRequest.getOidFandom()).orElseThrow(FandomNotFoundException::new);

        post.setContent(textContent);
        post.setCreator(user);
        post.setFandom(fandom);

        return postRepository.save(post);
    }

    @Override
    public Post getPost(Long oidPost) {
        return this.postRepository.findById(oidPost).orElseThrow(PostNotFoundException::new);
    }

    @Override
    public List<Post> getPostFeed(Long oidUser) {
        return postRepository.getPostFeed(oidUser);
    }

}
