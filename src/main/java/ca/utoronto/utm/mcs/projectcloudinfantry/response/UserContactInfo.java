package ca.utoronto.utm.mcs.projectcloudinfantry.response;

public class UserContactInfo {
    
    private Long oidUser;
    private String email;
    private String username;
    private String description;

    public Long getOidUser() {
        return oidUser;
    }

    public void setOidUser(Long oidUser) {
        this.oidUser = oidUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
