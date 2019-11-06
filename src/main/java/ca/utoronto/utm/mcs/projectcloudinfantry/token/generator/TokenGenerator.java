package ca.utoronto.utm.mcs.projectcloudinfantry.token.generator;

import java.util.Map;

public interface TokenGenerator {

    String generateToken(Long oidUser, Map<String, Object> claims);

}
