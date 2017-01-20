package lab.heisenbug.sandbox.java.math;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Oct 30, 2010
 * Time: 11:38:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class BigDecimalTest {

    private static final Logger logger = LoggerFactory.getLogger(BigDecimalTest.class);

    @Test
    public void createFromString() {
        String input = "00007438.7898379580000";
        BigDecimal salary = new BigDecimal(input).setScale(2, RoundingMode.DOWN);
        logger.info("value:{}, scale:{}, precision:{}", salary.toPlainString(), salary.scale(), salary.precision());

    }

}
