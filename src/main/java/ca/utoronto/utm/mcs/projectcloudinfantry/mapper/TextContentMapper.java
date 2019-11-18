package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.TextPostRequest;
import org.springframework.stereotype.Component;

@Component
public class TextContentMapper {
    public TextContent toTextContent(TextPostRequest textPostRequest) {
        TextContent textContent = new TextContent();
        textContent.setText(textPostRequest.getText());
        return textContent;
    }
}
