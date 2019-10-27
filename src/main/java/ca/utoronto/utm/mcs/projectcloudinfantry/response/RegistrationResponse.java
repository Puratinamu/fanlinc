package ca.utoronto.utm.mcs.projectcloudinfantry.response;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;

import java.util.ArrayList;
import java.util.List;

public class RegistrationResponse {

    private Long oidUser;
    private String email;
    private String username;
    private String description;
    private List<Long> fandoms;

    public RegistrationResponse(User user) {
        this.oidUser = user.getOidUser();
        this.email = MapperUtils.toEmptyIfNull(user.getEmail());
        this.username = MapperUtils.toEmptyIfNull(user.getUsername());
        this.description = MapperUtils.toEmptyIfNull(user.getDescription());
        List<Long> fandomIds = new ArrayList<>();
        // Get list of ids from fandoms
        for (Fandom f : user.getFandoms()) {
            fandomIds.add(f.getOidFandom());
        }
        this.fandoms = fandomIds;
    }

    public Long getOidUser() {
        return oidUser;
    }

    public String getEmail() {
        return email;
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
