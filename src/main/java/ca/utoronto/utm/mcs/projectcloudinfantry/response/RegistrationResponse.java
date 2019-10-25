package ca.utoronto.utm.mcs.projectcloudinfantry.response;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.utils.MapperUtils;

import java.util.List;

public class RegistrationResponse {

    private String oidUser;
    private String email;
    private String username;
    private String description;
    private List<Fandom> fandoms;

    public RegistrationResponse(User user) {
        this.oidUser = MapperUtils.toEmptyIfNull(user.getOidUser().toString());
        this.email = MapperUtils.toEmptyIfNull(user.getEmail());
        this.username = MapperUtils.toEmptyIfNull(user.getUsername());
        this.description = MapperUtils.toEmptyIfNull(user.getDescription());
        this.fandoms = user.getFandoms();
    }

    public String getOidUser() {
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

    public List<Fandom> getFandoms() {
        return fandoms;
    }
}
