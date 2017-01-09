package lab.heisenbug.sandbox;

import lab.heisenbug.sandbox.payroll.repositories.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by parker on 09/01/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SandboxApplicationTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    public void shouldGetRepositorInjected() throws Exception {
        Assert.assertNotNull(this.employeeRepository);
    }
}
