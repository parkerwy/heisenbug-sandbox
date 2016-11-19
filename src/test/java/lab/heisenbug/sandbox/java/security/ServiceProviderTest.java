package lab.heisenbug.sandbox.java.security;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Provider;
import java.security.Security;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Jun 6, 2010
 * Time: 10:20:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class ServiceProviderTest {

    private static final Logger logger = LoggerFactory.getLogger(ServiceProviderTest.class);

    @Test
    public void dumpServiceInfo() {
        Provider[] providers = Security.getProviders();
        for( Provider provider : providers){
            Set<Provider.Service> services =  provider.getServices();
            for( Provider.Service service : services){
                StringBuilder builder = new StringBuilder();
                builder.append(provider.getName());
                builder.append("\t");
                builder.append(service.getAlgorithm());
                builder.append("\t");
                builder.append(service.getType());
                logger.info(builder.toString());
            }
        }
    }

    
}
