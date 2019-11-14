package ca.utoronto.utm.mcs.projectcloudinfantry.controller;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.TextPostMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.TextPostRequestMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.TextPostResponseMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.TextPostRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.TextPostResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.service.TextPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TextPostController {

    private TextPostService textPostService;
    private TextPostMapper textPostMapper;
    private TextPostRequestMapper textPostRequestMapper;
    private TextPostResponseMapper textPostResponseMapper;

    public TextPostController(TextPostService textPostService, TextPostMapper textPostMapper) {
        this.textPostService = textPostService;
        this.textPostMapper = textPostMapper;
    }

    @RequestMapping(value = "/api/v1/getTextPost", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public TextContent getTextPost(@RequestBody Map<String, String> body, HttpServletResponse res) {
        return textPostService.getTextPost(Long.parseLong(body.get("oidTextPost")));
    }

    @RequestMapping(value = "/api/v1/addTextPost", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<TextPostResponse> addTextPost(@Valid @RequestBody Map<String, Object> body) {
        TextPostRequest textPostRequest = textPostRequestMapper.toTextPostRequest(body);
        TextContent textPost = textPostMapper.toTextPost(textPostRequest);
        TextContent textContent = textPostService.addTextPost(textPost);
        TextPostResponse response = textPostResponseMapper.toTextpostResponse(textContent);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
