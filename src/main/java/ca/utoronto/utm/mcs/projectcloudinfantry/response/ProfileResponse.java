package ca.utoronto.utm.mcs.projectcloudinfantry.response;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;

import java.util.List;

public class ProfileResponse {

    private Long oidUser;
    private String username;
    private String description;
    private List<UserFandomAndRelationshipInfo> fandoms;

    public ProfileResponse(User user, List<UserFandomAndRelationshipInfo> fandoms) {
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

    public List<UserFandomAndRelationshipInfo> getFandoms() {
        return fandoms;
    }
}
