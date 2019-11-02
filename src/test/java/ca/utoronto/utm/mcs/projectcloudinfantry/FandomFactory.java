package ca.utoronto.utm.mcs.projectcloudinfantry;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;

import java.util.Date;

import static java.time.LocalDate.now;

public class FandomFactory {

    public static Fandom createFandom(Long fandomId, String name) {
        Fandom fandom = new Fandom();
        fandom.setOidFandom(fandomId);
        fandom.setName(name);
        fandom.setDescription("General fandom description");
        fandom.setCreationTimestamp(new Date());
        fandom.setLastUpdateTimestamp(new Date());
        return fandom;
    }
}
