package ca.utoronto.utm.mcs.projectcloudinfantry.controller;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.TextPostMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.service.TextPostService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TextPostController {

    private TextPostService textPostService;
    private TextPostMapper textPostMapper;

    public TextPostController(TextPostService textPostService, TextPostMapper textPostMapper) {
        this.textPostService = textPostService;
        this.textPostMapper = textPostMapper;
    }

    @RequestMapping(value = "/api/v1/getTextPost", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public TextContent getTextPost(@RequestBody Map<String, String> body, HttpServletResponse res) {
        return textPostService.getTextPost(Long.parseLong(body.get("oidTextPost")));
    }

    @RequestMapping(value = "/api/v1/addTextPost", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public TextContent addTextPost(@RequestBody Map<String, Object> body) {
        TextContent textPost = textPostMapper.toTextPost(body);
        return textPostService.addTextPost(textPost);
    }
}
