package lab.heisenbug.sandbox.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by parker on 02/05/2017.
 */
@Component
@ConfigurationProperties("github")
public class Oauth2GithubProperties extends Oauth2Properties {

}
