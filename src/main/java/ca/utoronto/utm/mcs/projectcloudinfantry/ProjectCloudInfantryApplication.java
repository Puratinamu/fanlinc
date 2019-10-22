package ca.utoronto.utm.mcs.projectcloudinfantry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ProjectCloudInfantryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectCloudInfantryApplication.class, args);
	}

}
