package ca.utoronto.utm.mcs.projectcloudinfantry.datafactory;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Post;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.Content;

import java.util.Date;

public class PostFactory {

    public static Post createPost(String title, User creator, Fandom fandom, Content content) {
        Post post = new Post();
        post.setTitle(title);
        post.setCreator(creator);
        post.setFandom(fandom);
        post.setContent(content);
        Date date = new Date();
        post.setCreationTimestamp(date);
        post.setLastUpdateTimestamp(date);
        return post;
    }

}
