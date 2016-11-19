package lab.heisenbug.sandbox.java.security;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Jun 6, 2010
 * Time: 10:37:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class MessageDigestTest {

    private static final Logger logger = LoggerFactory.getLogger(MessageDigestTest.class);

    private static final String CLEAR_TEXT = "hello, world!";

    @Test
    public void genMd5Digest() throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] hash = md5.digest(CLEAR_TEXT.getBytes());
        logger.info(Arrays.toString(hash));
        logger.info("total " + hash.length);
    }

    @Test
    public void genSHADigest() throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA");
        byte[] hash = sha.digest(CLEAR_TEXT.getBytes());
        logger.info(Arrays.toString(hash));
        logger.info("total " + hash.length);
    }
}
