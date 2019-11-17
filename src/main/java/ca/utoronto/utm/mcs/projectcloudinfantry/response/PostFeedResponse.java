package ca.utoronto.utm.mcs.projectcloudinfantry.response;

import java.util.List;

public class PostFeedResponse {

    private List<TextPostResponse> posts;

    public List<TextPostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<TextPostResponse> posts) {
        this.posts = posts;
    }
}
