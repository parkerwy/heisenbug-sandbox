package lab.heisenbug.sandbox.java.security;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Jun 6, 2010
 * Time: 10:17:23 AM
 * To change this template use File | Settings | File Templates.
 */

public class SecureRandomTest {

    private static final Logger logger = LoggerFactory.getLogger(SecureRandomTest.class);

    @Test
    public void testGenRandom() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.doubles(100).forEach(
                (n) -> logger.info(Double.toString(n))
        );
    }
}
