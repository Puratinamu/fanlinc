package ca.utoronto.utm.mcs.projectcloudinfantry;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;

import java.util.ArrayList;

public class FandomFactory {
    public static Fandom CreateFandom(String name) {
        Fandom fandom = new Fandom();
        fandom.setName(name);
        fandom.setDescription("A fandom");
        fandom.setMembers(new ArrayList<>());
        fandom.setPosts(new ArrayList<>());
        return fandom;
    }
}
