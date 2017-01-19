package lab.heisenbug.sandbox.payroll.actuator;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Service;

/**
 * Created by parker on 19/01/2017.
 */
@Service
public class PayrollHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up().withDetail("token", RandomStringUtils.random(10));
    }
}
