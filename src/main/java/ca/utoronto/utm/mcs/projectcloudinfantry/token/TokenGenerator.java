package ca.utoronto.utm.mcs.projectcloudinfantry.token;

import java.util.Map;

public interface TokenGenerator {

    String generateToken(Long oidUser, Map<String, Object> claims);

}
