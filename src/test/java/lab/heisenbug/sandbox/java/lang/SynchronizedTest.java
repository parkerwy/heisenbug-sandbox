package lab.heisenbug.sandbox.java.lang;

import org.junit.Ignore;
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
 * Date: Jul 21, 2010
 * Time: 10:36:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class SynchronizedTest {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizedTest.class);

    private static Random random = new Random();

    public static void longSleep() {
        try {
            long duration = (long) (random.nextFloat() * 1000);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void shortSleep() {
        try {
            long duration = (long) (random.nextFloat() * 10);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }


    @Test
    @Ignore
    public void concurrentInvoke() throws InterruptedException {
        logger.info("Started.");
        ConcurrentTarget x = new ConcurrentTarget("x");
        //final ConcurrentTarget y = new ConcurrentTarget("y");

        ExecutorService service = Executors.newFixedThreadPool(6);

        service.execute(new FooInvoker(x));
        service.execute(new FooInvoker(x));
        service.execute(new BarInvoker(x));
        service.execute(new BarInvoker(x));
        service.execute(new FooBarInvoker(x));
        service.execute(new FooBarInvoker(x));

        service.shutdown();
        service.awaitTermination(60, TimeUnit.SECONDS);
        logger.info("Completed.");
    }


    public static class FooInvoker implements Runnable {

        private ConcurrentTarget target;

        protected FooInvoker(ConcurrentTarget target) {
            this.target = target;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                target.foo();
                shortSleep();
            }
        }
    }

    public static class BarInvoker implements Runnable {

        private ConcurrentTarget target;

        protected BarInvoker(ConcurrentTarget target) {
            this.target = target;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                target.bar();
                shortSleep();
            }
        }
    }

    public static class FooBarInvoker implements Runnable {

        private ConcurrentTarget target;

        protected FooBarInvoker(ConcurrentTarget target) {
            this.target = target;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                target.foobar();
                shortSleep();
            }
        }
    }

    public static class ConcurrentTarget {


        private String name;

        public ConcurrentTarget(String name) {
            this.name = name;
        }

        public static synchronized void foobar() {
            String thread = Thread.currentThread().getName();
            logger.info("{} ===> entering block synchronized on class.", thread);
            longSleep();
            logger.info("{} <=== leaving block synchronized on class.", thread);

        }

        public synchronized void foo() {
            String thread = Thread.currentThread().getName();
            logger.info("{} ---> entering block synchronized on object {}.", thread, name);
            longSleep();
            logger.info("{} <--- leaving block synchronized on object {}.", thread, name);
        }

        public synchronized void bar() {
            String thread = Thread.currentThread().getName();
            logger.info("{} -.-.-> entering non-synchronized block of object {}.", thread, name);
            longSleep();
            logger.info("{} <-.-.- leaving non-synchronized block of object {}.", thread, name);
        }
    }

}
