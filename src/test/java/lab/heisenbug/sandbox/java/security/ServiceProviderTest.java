package lab.heisenbug.sandbox.java.security;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import java.util.Comparator;

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
        Arrays.stream(Security.getProviders())
                .flatMap((provider) -> provider.getServices().stream())
                .sorted(Comparator.comparing((Provider.Service s) -> s.getProvider().getName())
                        .thenComparing(Provider.Service::getAlgorithm))
                .forEach(
                        (service) -> logger.info("{}\t{}\t{}",
                                service.getProvider().getName(),
                                service.getAlgorithm(),
                                service.getType())
                );
    }
}
