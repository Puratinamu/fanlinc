package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.request.TextPostRequest;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TextPostRequestMapper {

    public TextPostRequest toTextPostRequest(Map<String, Object> body) {
        TextPostRequest textPostRequest = new TextPostRequest();
        textPostRequest.setOidCreator((Long) body.get("oidCreator"));
        textPostRequest.setOidFandom((Long) body.get("oidFandom"));
        textPostRequest.setText(String.valueOf(body.get("text")));
        return textPostRequest;
    }

}
