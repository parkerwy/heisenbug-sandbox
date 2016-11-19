package lab.heisenbug.sandbox.java.lang;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Sep 4, 2010
 * Time: 6:47:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThreadLocalTest {

    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalTest.class);

    private ThreadLocal<Integer> count = new ThreadLocal<Integer>();


    @Test
    public void concurrentAccess() throws InterruptedException {
        logger.info("Started.");

        ExecutorService service = Executors.newFixedThreadPool(6);
        service.execute(new ThreadLocalAccessor(this));
        service.execute(new ThreadLocalAccessor(this));
        service.execute(new ThreadLocalAccessor(this));
        service.execute(new ThreadLocalAccessor(this));
        service.execute(new ThreadLocalAccessor(this));
        service.execute(new ThreadLocalAccessor(this));

        service.shutdown();
        service.awaitTermination(60, TimeUnit.SECONDS);
        logger.info("Completed.");
    }


    private static class ThreadLocalAccessor implements Runnable {

        Random random = new Random();

        ThreadLocalTest target;

        public ThreadLocalAccessor(ThreadLocalTest target) {
            this.target = target;
        }

        @Override
        public void run() {
            try {
                String name = Thread.currentThread().getName();
                for (int i = 0; i < 10; i++) {
                    int duration = (int) (random.nextFloat() * 1000);
                    target.count.set(duration);
                    logger.info("{} set ===> {}", name, duration);
                    Thread.sleep(duration);
                    logger.info("{} get <--- {}", name, target.count.get());
                }
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
