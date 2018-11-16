package lab.heisenbug.sandbox.java.lang;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Sep 4, 2010
 * Time: 6:47:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThreadLocalTest {

    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalTest.class);

    private ThreadLocal<Integer> count = new ThreadLocal<>();


    @Test
    public void concurrentAccess() throws InterruptedException {
        logger.info("Started.");

        ExecutorService service = Executors.newFixedThreadPool(6);
        IntStream.rangeClosed(1, 5).forEach(
                (i) -> service.execute(new ThreadLocalAccessor(this))
        );

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
            String name = Thread.currentThread().getName();
            random.ints(10L, 0, 1000).forEach(
                    (duration) -> {
                        try {
                            target.count.set(duration);
                            logger.info("{} set ===> {}", name, duration);
                            Thread.sleep(duration);
                            logger.info("{} get <--- {}", name, target.count.get());
                        } catch (InterruptedException e) {
                            logger.error(e.getMessage(), e);
                        }
                    }
            );
        }
    }
}
