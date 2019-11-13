package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.request.ContentRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.TextPostRequest;

import java.util.Map;

public class TextPostRequestMapper {
    public static TextPostRequest toTextPostRequest(Map<String, Object> body){
        TextPostRequest textPostRequest = new TextPostRequest();
        textPostRequest.setOidFandom(Long.valueOf((String) body.get("oidFandom")));
        textPostRequest.setOidUser(Long.valueOf((String) body.get("oidUser")));
        textPostRequest.setText((String) body.get("text"));
        return textPostRequest;
    }
}
