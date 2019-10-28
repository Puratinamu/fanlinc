package ca.utoronto.utm.mcs.projectcloudinfantry.response;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;

import java.util.ArrayList;
import java.util.List;

public class ProfileResponse {

    private String oidUser;
    private String username;
    private String description;
    private List<Long> fandoms;

    public ProfileResponse(User user) {
        this.oidUser = user.getOidUser().toString();
        this.username = user.getUsername();
        this.description = user.getDescription();
        this.fandoms = new ArrayList<>();
        for (Fandom fandom: user.getFandoms()) {
            this.fandoms.add(fandom.getOidFandom());
        }
    }

    public String getOidUser() {
        return oidUser;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public List<Long> getFandoms() {
        return fandoms;
    }
}
