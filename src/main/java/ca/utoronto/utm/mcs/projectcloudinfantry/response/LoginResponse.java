package ca.utoronto.utm.mcs.projectcloudinfantry.response;

public class LoginResponse {
    private Long oidUser;
    private String jwt;


    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getOidUser() {
        return oidUser;
    }

    public void setOidUser(Long oidUser) {
        this.oidUser = oidUser;
    }
}
