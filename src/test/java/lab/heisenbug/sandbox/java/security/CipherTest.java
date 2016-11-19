package lab.heisenbug.sandbox.java.security;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Jun 6, 2010
 * Time: 10:51:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class CipherTest {

    private static final Logger logger = LoggerFactory.getLogger(CipherTest.class);

    @Test
    public void encrypt() throws NoSuchAlgorithmException, NoSuchPaddingException {

        Cipher c1 = Cipher.getInstance("DES");
    }
}
