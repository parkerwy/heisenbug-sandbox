package lab.heisenbug.sandbox.java.util.concurrent;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: 1/30/11
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExecutorServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorServiceTest.class);

    @Test
    public void timeoutOnInvoke() {
        ExecutorService service = Executors.newFixedThreadPool(2);

        List<Future> results = new ArrayList<Future>();

        for (int i = 0; i < 10; i++) {
            Future reslut = service.submit(new Task("Job" + (i + 1)));
            results.add(reslut);
        }

        for (Future result : results) {
            try {
                result.get(300, TimeUnit.MICROSECONDS);
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            } catch (ExecutionException e) {
                logger.error(e.getMessage(), e);
            } catch (TimeoutException e) {
                logger.info("time out on get the result, tring to cancel the job.");
                result.cancel(true);
            }
        }
    }

    private static class Task implements Runnable {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private String name;

        public Task(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            logger.info("job {} started running on thread {}.", name, Thread.currentThread().hashCode());
            long x = 0;
            for (int i = 0; i < 900000000; i++) {
                x = x * i;
            }
            logger.info("job {} completed running.", name);
        }
    }
}
