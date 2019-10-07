package ca.utoronto.utm.mcs.projectcloudinfantry;

import org.testcontainers.containers.GenericContainer;

public class Neo4jContainerStarter {

    private static GenericContainer DB_CONTAINER = startContainer();
    private static final int port = 7687;

    private static GenericContainer startContainer() {
        GenericContainer neo4j = new GenericContainer("neo4j:3.5.0")
                .withEnv("NEO4J_dbms_security_auth__enabled", "false")
                .withExposedPorts(port);
        neo4j.start();
        return neo4j;
    }

    public static String uri() {
        return "bolt://" + DB_CONTAINER.getContainerIpAddress() + ":" + DB_CONTAINER.getMappedPort(port);
    }

}
