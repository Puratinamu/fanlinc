package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.TextPostRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;
import org.springframework.stereotype.Component;

@Component
public class TextPostMapper {

    public TextContent toTextPost(TextPostRequest body) {
        TextContent textPost = new TextContent();
        textPost.setOidFandom(body.getOidFandom());
        textPost.setOidUser(body.getOidUser());
        textPost.setText(body.getText());
        return textPost;
    }
}
