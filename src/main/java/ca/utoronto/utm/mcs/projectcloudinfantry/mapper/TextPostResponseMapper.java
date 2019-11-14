package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Post;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.TextPostResponse;
import org.springframework.stereotype.Component;

@Component
public class TextPostResponseMapper {

    public TextPostResponse toTextPostResponse(Post post) {
        TextPostResponse textPostResponse = new TextPostResponse();
        textPostResponse.setOidPost(post.getOidPost());
        textPostResponse.setOidCreator(post.getCreator().getOidUser());
        textPostResponse.setOidFandom(post.getFandom().getOidFandom());
        return textPostResponse;
    }
}
