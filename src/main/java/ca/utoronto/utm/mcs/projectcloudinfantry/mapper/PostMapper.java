package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Post;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.TextPostRequest;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post toPost(TextPostRequest textPostRequest) {
        Post post = new Post();
        post.setTitle(textPostRequest.getTitle());
        return post;
    }
}
