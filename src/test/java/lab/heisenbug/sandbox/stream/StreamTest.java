package lab.heisenbug.sandbox.stream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamTest.class);

    @Test
    public void shouldRunParallelSteam() throws Exception {
        IntStream.range(1, 100)
                .boxed()
                .parallel()
                .filter(value -> {
                    LOGGER.info("processing {}", value);
                    return true;
                })
                .collect(Collectors.toList());
    }
}
