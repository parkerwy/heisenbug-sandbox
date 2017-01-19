package lab.heisenbug.sandbox.payroll.actuator;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;

/**
 * Created by parker on 19/01/2017.
 */
@Controller
@ConfigurationProperties(prefix = "payroll.population")
public class PayrollPopulationEndpoint extends AbstractEndpoint<String> {

    public PayrollPopulationEndpoint() {
        super("payrollPopulation");
    }

    @Override
    public String invoke() {
        return "Done";
    }
}
