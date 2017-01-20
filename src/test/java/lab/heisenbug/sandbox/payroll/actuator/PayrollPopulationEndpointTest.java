package lab.heisenbug.sandbox.payroll.actuator;

import lab.heisenbug.sandbox.SandboxApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by parker on 20/01/2017.
 */
public class PayrollPopulationEndpointTest extends SandboxApplicationTest {

    @Autowired
    private PayrollPopulationEndpoint payrollPopulationEndpoint;

    @Test
    public void shouldPopulateData() throws Exception {
        this.payrollPopulationEndpoint.invoke();
    }
}
