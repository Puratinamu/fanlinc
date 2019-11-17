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
        textPostResponse.setUsername(post.getCreator().getUsername());
        textPostResponse.setOidFandom(post.getFandom().getOidFandom());
        textPostResponse.setFandomName(post.getFandom().getName());
        textPostResponse.setTitle(post.getTitle());
        textPostResponse.setCreationTimestamp(String.valueOf(post.getCreationTimestamp()));

        TextContent textContent = (TextContent) post.getContent();
        textPostResponse.setText(textContent.getText());
        return textPostResponse;
    }
}
