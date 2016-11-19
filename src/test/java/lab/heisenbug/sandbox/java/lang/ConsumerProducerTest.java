package lab.heisenbug.sandbox.java.lang;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Aug 7, 2010
 * Time: 11:34:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConsumerProducerTest {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerProducerTest.class);

    @Test
    public void consumeAndProduce() throws InterruptedException {
        Queue<Integer> pool = new LinkedList<Integer>();
        Consumer con1 = new Consumer(pool);
        Consumer con2 = new Consumer(pool);

        Producer pro1 = new Producer(pool);
        Producer pro2 = new Producer(pool);

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(con1);
        executorService.execute(con2);
        executorService.execute(pro1);
        executorService.execute(pro2);

        Thread.sleep(5 * 1000);
        con1.stop();
        con2.stop();
        Thread.sleep(2 * 1000);
        pro1.stop();
        pro2.stop();


        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    private static class Consumer implements Runnable {

        private Queue<Integer> pool;

        private boolean execute = true;

        private Random random = new Random();

        private Consumer(Queue<Integer> pool) {
            this.pool = pool;
        }

        @Override
        public void run() {

            String name = Thread.currentThread().getName();

            try {
                while (execute) {
                    logger.info("consumer {} entered a new cycle", name);
                    synchronized (pool) {
                        logger.info("consumer {} locked the pool", name);
                        while (pool.isEmpty()) {
                            logger.info("consumer {} will wait on the pool", name);
                            pool.wait();
                            logger.info("consumer {} is awaken", name);
                        }
                        Integer i = pool.poll();                        
                        logger.info("consumer {} consumed number <=== {}", name, i);
                        pool.notifyAll();
                    }
                    Thread.sleep((int) (random.nextFloat() * 1000));
                }
                logger.info("consumer stoped.");
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }

        private void stop() {
            execute = false;
        }
    }


    private static class Producer implements Runnable {

        private static final int BUFFER_SIZE = 2;

        private Queue<Integer> pool;

        private boolean execute = true;

        private Random random = new Random();

        private Producer(Queue<Integer> pool) {
            this.pool = pool;
        }

        @Override
        public void run() {

            String name = Thread.currentThread().getName();

            try {
                while (execute) {
                    Integer i = (int) (random.nextFloat() * 1000);
                    Thread.sleep(i);
                    synchronized (pool) {
                        logger.info("producer {} locked the pool", name);
                        while (pool.size() >= BUFFER_SIZE) {
                            logger.info("producer {} will wait on the pool", name);
                            pool.wait();
                            logger.info("producer {} is awaken", name);
                        }
                        pool.add(i);
                        logger.info("producer {} produced number ===> {}", name, i);
                        pool.notifyAll();
                    }
                }
                logger.info("producer stoped.");
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }

        private void stop() {
            execute = false;
        }
    }
}
