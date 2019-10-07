package ca.utoronto.utm.mcs.projectcloudinfantry


import org.junit.Ignore
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import spock.lang.Specification

@Ignore
@SpringBootTest(classes = ProjectCloudInfantryApplication.class)
class BaseSpecification extends Specification {

    @Configuration
    static class ContainerConfig {
        @Bean
        org.neo4j.ogm.config.Configuration configuration() {
            return new org.neo4j.ogm.config.Configuration.Builder()
                    .uri(Neo4jContainerStarter.uri())
                    .credentials("neo4j", "neo4j")
                    .build()
        }
    }

}
