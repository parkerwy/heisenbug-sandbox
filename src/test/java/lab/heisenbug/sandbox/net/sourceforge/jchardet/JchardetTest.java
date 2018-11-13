package lab.heisenbug.sandbox.net.sourceforge.jchardet;

import org.junit.Test;
import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;
import org.mozilla.intl.chardet.nsPSMDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Nov 7, 2010
 * Time: 10:17:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class JchardetTest {

    private static final Logger logger = LoggerFactory.getLogger(JchardetTest.class);

    @Test
    public void detectString() throws IOException {
        nsDetector detector = new nsDetector(nsPSMDetector.ALL);
        detector.Init((s) -> logger.info("detected charset: {}", s));

        URL url = new URL("http://www.163.com");
        BufferedInputStream imp = new BufferedInputStream(url.openStream());

        byte[] buf = new byte[1024];
        int len;
        boolean done = false;
        boolean isAscii = true;

        while ((len = imp.read(buf, 0, buf.length)) != -1) {

            // Check if the stream is only ascii.
            if (isAscii)
                isAscii = detector.isAscii(buf, len);

            // DoIt if non-ascii and not done yet.
            if (!isAscii && !done)
                done = detector.DoIt(buf, len, false);
        }
        detector.DataEnd();

        if (isAscii) {
            System.out.println("CHARSET = ASCII");
        }
    }

}
