package lab.heisenbug.sandbox.rxjava;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Observable;

public class ObservablesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObservablesTest.class);

    @Test
    public void shouldSubscribe() throws Exception {
        Observable.range(1, 100)
                .subscribe(integer -> LOGGER.info("got next value as {}", integer));

        Observable.just(8, 9, 10)
                .filter(i -> i % 3 > 0)
                .map(i -> "#" + i * 10)
                .filter(s -> s.length() < 4)
                .subscribe(System.out::println);
    }

    @Test
    public void shouldUseFlatMap() throws Exception {
        Observable.range(1, 10)
                .flatMap(i -> Observable.range(i, 2))
                .subscribe(System.out::println);

    }
}
