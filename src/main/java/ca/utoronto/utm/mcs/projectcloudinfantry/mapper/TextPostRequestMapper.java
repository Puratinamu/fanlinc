package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.request.TextPostRequest;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TextPostRequestMapper {

    public TextPostRequest toTextPostRequest(Map<String, Object> body) {
        TextPostRequest textPostRequest = new TextPostRequest();
        textPostRequest.setOidCreator(Long.valueOf(body.get("oidCreator").toString()));
        textPostRequest.setOidFandom(Long.valueOf(body.get("oidFandom").toString()));
        textPostRequest.setTitle(body.get("title").toString());

        textPostRequest.setText(String.valueOf(body.get("text")));
        return textPostRequest;
    }

}
