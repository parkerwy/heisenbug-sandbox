package lab.heisenbug.sandbox.security;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by parker on 03/06/2017.
 */
@Configuration
public class Oauth2RestTemplateConfiguration {

    @Bean
    public Map<Oauth2Properties, OAuth2RestTemplate> oauth2RestTemplates(List<Oauth2Properties> oauth2PropertiesList,
                                                                         OAuth2ClientContext oauth2ClientContext) {
        Map<Oauth2Properties, OAuth2RestTemplate> providers = new HashMap<>();
        for (Oauth2Properties oauth2Properties : oauth2PropertiesList) {
            OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(oauth2Properties.getClient(), oauth2ClientContext);
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
            OkHttp3ClientHttpRequestFactory requestFactory = new OkHttp3ClientHttpRequestFactory(okHttpClient);
            oAuth2RestTemplate.setRequestFactory(requestFactory);
            providers.put(oauth2Properties, oAuth2RestTemplate);
        }
        return providers;
    }
}
