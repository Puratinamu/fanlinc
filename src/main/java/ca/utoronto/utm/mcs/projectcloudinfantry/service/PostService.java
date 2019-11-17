package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Post;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.TextPostRequest;

import java.util.List;

public interface PostService {

    Post addTextPost(TextPostRequest textPostRequest);

    Post getPost(Long oidPost);

    List<Post> getPostFeed(Long oidUser);

}
