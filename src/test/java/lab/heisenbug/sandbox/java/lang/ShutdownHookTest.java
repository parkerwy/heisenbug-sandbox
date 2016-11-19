package lab.heisenbug.sandbox.java.lang;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Aug 11, 2010
 * Time: 10:00:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShutdownHookTest {

    private static final Logger logger = LoggerFactory.getLogger(ShutdownHookTest.class);

    @Test
    @Ignore
    public void longShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.info("prepare to shut down.");
                try {
                    Thread.sleep(10 * 60 * 1000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
                logger.info("shuting down.");
            }
        });
    }

}
