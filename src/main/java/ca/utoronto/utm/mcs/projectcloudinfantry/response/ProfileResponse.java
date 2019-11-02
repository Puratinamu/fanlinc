package ca.utoronto.utm.mcs.projectcloudinfantry.response;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.ArrayList;
import java.util.List;

public class ProfileResponse {

    private Long oidUser;
    private String username;
    private String description;
    private List<FandomInfo> fandoms;

    public ProfileResponse(User user, List<FandomInfo> fandoms) {
        this.oidUser = user.getOidUser();
        this.username = user.getUsername();
        this.description = user.getDescription();
        this.fandoms = fandoms;
    }

    public Long getOidUser() {
        return oidUser;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public List<FandomInfo> getFandoms() {
        return fandoms;
    }
}
