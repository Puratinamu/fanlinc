package ca.utoronto.utm.mcs.projectcloudinfantry.datafactory;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent;

public class ContentFactory {

    public static TextContent createTextContent(String text) {
        TextContent textContent = new TextContent();
        textContent.setText(text);
        return textContent;
    }

}
