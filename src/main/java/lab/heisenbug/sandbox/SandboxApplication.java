package lab.heisenbug.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * Created by parker on 19/11/2016.
 */
@SpringBootApplication
@EnableOAuth2Client
public class SandboxApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SandboxApplication.class);
        app.setWebApplicationType(WebApplicationType.REACTIVE);
        app.run(args);
    }
}
