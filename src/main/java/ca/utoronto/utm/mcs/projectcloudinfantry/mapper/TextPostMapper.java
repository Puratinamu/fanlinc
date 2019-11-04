package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TextPostMapper {

    public TextContent toTextPost(Map<String, Object> body) {
        TextContent textPost = new TextContent();
        textPost.setOidFandom(Long.valueOf((String) body.get("oidFandom")));
        textPost.setOidUser(Long.valueOf((String) body.get("oidUser")));
        textPost.setText(MapperUtils.toEmptyIfNull(body.get("text")));
        return textPost;
    }
}
