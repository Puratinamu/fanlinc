package ca.utoronto.utm.mcs.projectcloudinfantry.controller;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Post;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.PostFeedResponseMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.TextPostRequestMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.TextPostResponseMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.TextPostRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.PostFeedResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.TextPostResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.service.PostService;
import ca.utoronto.utm.mcs.projectcloudinfantry.token.TokenService;
import org.springframework.http.HttpHeaders;
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
    private final PostFeedResponseMapper postFeedResponseMapper;
    private final TokenService tokenService;

    public PostController(PostService postService,
                          TextPostRequestMapper textPostRequestMapper,
                          TextPostResponseMapper textPostResponseMapper,
                          PostFeedResponseMapper postFeedResponseMapper,
                          TokenService tokenService) {
        this.postService = postService;
        this.textPostRequestMapper = textPostRequestMapper;
        this.textPostResponseMapper = textPostResponseMapper;
        this.postFeedResponseMapper = postFeedResponseMapper;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "/api/v1/addTextPost", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<TextPostResponse> addTextPost(@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> body) {
        try {
            tokenService.authenticate(headers.getFirst("jwt"), Long.valueOf(String.valueOf(body.get("oidCreator"))));
            TextPostRequest textPostRequest = textPostRequestMapper.toTextPostRequest(body);
            Post post = postService.addTextPost(textPostRequest);
            TextPostResponse textPostResponse = textPostResponseMapper.toTextPostResponse(post);
            return new ResponseEntity<>(textPostResponse, HttpStatus.OK);
        } catch (FandomNotFoundException | UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/v1/getPostFeed", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PostFeedResponse> getPostFeed(@RequestHeader HttpHeaders headers, @RequestParam String oidUser) {
        try {
            tokenService.authenticate(headers.getFirst("jwt"), Long.valueOf(oidUser));
            List<Post> posts = postService.getPostFeed(Long.valueOf(oidUser));
            PostFeedResponse postFeedResponse = postFeedResponseMapper.toPostFeedResponse(posts);
            return new ResponseEntity<>(postFeedResponse, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
