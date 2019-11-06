package ca.utoronto.utm.mcs.projectcloudinfantry.token;

import io.jsonwebtoken.Claims;

import java.util.Map;

public interface TokenService {

    String generateToken(Long oidUser, Map<String, Object> claims);
    Claims extractToken(String token);
    boolean authenticate(String token, Long oidUser);

}
