package lab.heisenbug.sandbox.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by parker on 02/05/2017.
 */
@Component
@ConfigurationProperties("google")
public class Oauth2GoogleProperties extends Oauth2Properties {

}
