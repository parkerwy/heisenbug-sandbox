package lab.heisenbug.sandbox.java.util.concurrent;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA. User: parker Date: 2/8/11 Time: 10:10 PM To change
 * this template use File | Settings | File Templates.
 */
public class DoubleCheckTest {

    private static final Logger logger = LoggerFactory.getLogger(DoubleCheckTest.class);

    public static void main(String[] args) throws InterruptedException {
        new DoubleCheckTest().getSingletonInstance();
    }

    @Test
    public void getSingletonInstance() throws InterruptedException {
        List<Callable<Object>> tasks = Stream.generate(Task::new)
                .limit(100)
                .collect(Collectors.toList());

        ScheduledThreadPoolExecutor service = new ScheduledThreadPoolExecutor(100);
        service.invokeAll(tasks);
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
    }


    public static class Task implements Callable<Object> {

        @Override
        public Object call() throws Exception {
            Singleton.getInstance();
            return null;
        }
    }

    public static class Singleton {

        private static AtomicInteger count = new AtomicInteger(0);

        private static Singleton instance;

        private Singleton() {
            int c = count.getAndIncrement();
            if (c > 0) {
                throw new IllegalStateException("Instance has been created for this class.");
            }
        }

        public static Singleton getInstance() {
            if (instance == null) {
                synchronized (Singleton.class) {
                    if (instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }
}
