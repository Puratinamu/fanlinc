package ca.utoronto.utm.mcs.projectcloudinfantry.controller;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Post;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.PostMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.TextContentMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.TextPostRequestMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.TextPostResponseMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.TextPostRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.TextPostResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class PostController {

    private final PostService postService;
    private final TextPostRequestMapper textPostRequestMapper;
    private final TextPostResponseMapper textPostResponseMapper;

    public PostController(PostService postService,
                          TextPostRequestMapper textPostRequestMapper,
                          TextPostResponseMapper textPostResponseMapper) {
        this.postService = postService;
        this.textPostRequestMapper = textPostRequestMapper;
        this.textPostResponseMapper = textPostResponseMapper;
    }

    @RequestMapping(value = "/api/v1/addTextPost", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<TextPostResponse> addTextPost(@RequestBody Map<String, Object> body) {
        try {
            TextPostRequest textPostRequest = textPostRequestMapper.toTextPostRequest(body);
            Post post = postService.addTextPost(textPostRequest);
            TextPostResponse textPostResponse = textPostResponseMapper.toTextPostResponse(post);
            return new ResponseEntity<>(textPostResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/v1/getPostFeed", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Post>> getPostFeed(@RequestParam String oidUser) {
        try {
            List<Post> posts = postService.getPostFeed(Long.valueOf(oidUser));
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
