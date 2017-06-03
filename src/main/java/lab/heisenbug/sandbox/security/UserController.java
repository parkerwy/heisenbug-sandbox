package lab.heisenbug.sandbox.security;

import org.apache.commons.lang3.AnnotationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by parker on 19/11/2016.
 */
@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private Map<String, String> oauth2Providers = new LinkedHashMap<>();

    private Map<String, OAuth2RestTemplate> oauth2RestTemplates = new LinkedHashMap<>();

    @Autowired
    public UserController(List<Oauth2Properties> oauth2PropertiesList, Map<Oauth2Properties, OAuth2RestTemplate> templates) {
        for (Oauth2Properties oauth2Properties : oauth2PropertiesList) {
            String clientId = oauth2Properties.getClient().getClientId();
            String provider = oauth2Properties.getClass().getAnnotation(ConfigurationProperties.class).value();
            oauth2Providers.put(clientId, provider);
            oauth2RestTemplates.put(clientId, templates.get(oauth2Properties));
            LOGGER.info("Configured OAuth2 provider {}", provider);
        }
    }

    @RequestMapping({"/user"})
    @ResponseBody
    public Map<String, Object> user(Principal principal, OAuth2Authentication oauth2) {
        Map<String, Object> userDetail = new LinkedHashMap<>();

        if (principal == null) {
            return userDetail;
        }

        userDetail.put("name", principal.getName());

        String clientId = oauth2.getOAuth2Request().getClientId();
        String provider = oauth2Providers.get(clientId);
        userDetail.put("provider", provider);

        OAuth2RestTemplate oauth2RestTemplate = oauth2RestTemplates.get(clientId);
        oauth2RestTemplate.getForObject("https://api.github.com/user/repos", String.class);

        return userDetail;
    }
}
