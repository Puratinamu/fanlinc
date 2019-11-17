package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Post;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.PostFeedResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.TextPostResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostFeedResponseMapper {

    private TextPostResponseMapper textPostResponseMapper;

    public PostFeedResponseMapper(TextPostResponseMapper textPostResponseMapper) {
        this.textPostResponseMapper = textPostResponseMapper;
    }

    public PostFeedResponse toPostFeedResponse(List<Post> posts) {
        PostFeedResponse postFeedResponse = new PostFeedResponse();
        List<TextPostResponse> textPostResponses = new ArrayList<>();
        for (Post post : posts) {
            textPostResponses.add(textPostResponseMapper.toTextPostResponse(post));
        }
        postFeedResponse.setPosts(textPostResponses);
        return postFeedResponse;
    }
}
