package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.TextPostResponse;
import org.springframework.stereotype.Component;


@Component
public class TextPostResponseMapper {

    public static TextPostResponse toTextpostResponse(TextContent body){
        TextPostResponse textPostResponse = new TextPostResponse();
        textPostResponse.setOidContent(body.getOidContent());
        textPostResponse.setOidFandom(body.getOidFandom());
        textPostResponse.setOidUser(body.getOidUser());
        textPostResponse.setText(body.getText());
        return textPostResponse;
    }
}
