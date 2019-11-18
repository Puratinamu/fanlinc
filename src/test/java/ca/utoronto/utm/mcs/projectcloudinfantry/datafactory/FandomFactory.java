package ca.utoronto.utm.mcs.projectcloudinfantry.datafactory;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import java.util.ArrayList;
import java.util.Date;

public class FandomFactory {

    public static Fandom createFandom(String name) {
        Fandom fandom = new Fandom();
        fandom.setName(name);
        fandom.setDescription("General fandom description");
        fandom.setMembers(new ArrayList<>());
        fandom.setPosts(new ArrayList<>());
        fandom.setCreationTimestamp(new Date());
        fandom.setLastUpdateTimestamp(new Date());
        return fandom;
    }
}
