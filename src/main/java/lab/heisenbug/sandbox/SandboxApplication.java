package lab.heisenbug.sandbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by parker on 19/11/2016.
 */
@SpringBootApplication
public class SandboxApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SandboxApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        LOGGER.info("building spring boot application.");
        return builder.sources(SandboxApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SandboxApplication.class, args);
    }
}
