package lab.heisenbug.sandbox.payroll.actuator;

import lab.heisenbug.sandbox.SandboxApplicationTest;
import lab.heisenbug.sandbox.payroll.domain.*;
import lab.heisenbug.sandbox.payroll.repositories.EmployeeRepository;
import lab.heisenbug.sandbox.payroll.repositories.EmployeeSpecs;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Created by parker on 20/01/2017.
 */
public class PayrollPopulationEndpointTest extends SandboxApplicationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayrollPopulationEndpointTest.class);

    @Autowired
    private PayrollPopulationEndpoint payrollPopulationEndpoint;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void shouldPopulateData() throws Exception {
        this.payrollPopulationEndpoint.populate();

        {
            List<Employee> employeeList = this.employeeRepository.findAll(EmployeeSpecs.ofClassification(CommissionedClassification.class));
            Assertions.assertThat(employeeList).isNotEmpty();
            employeeList.forEach(employee -> LOGGER.info("loaded employee [{}] with Commissioned Classification.", employee.getName()));
        }

        {
            List<Employee> employeeList = this.employeeRepository.findAll(EmployeeSpecs.ofClassification(HourlyClassification.class));
            Assertions.assertThat(employeeList).isNotEmpty();
            employeeList.forEach(employee -> LOGGER.info("loaded employee [{}] with Hourly Classification.", employee.getName()));
        }

        {
            List<Employee> employeeList = this.employeeRepository.findAll(EmployeeSpecs.ofClassification(SalariedClassification.class));
            Assertions.assertThat(employeeList).isNotEmpty();
            employeeList.forEach(employee -> LOGGER.info("loaded employee [{}] with Salaried Classification.", employee.getName()));
        }

        this.employeeRepository.findAll(QEmployee.employee.name.startsWith("P").and(QEmployee.employee.phone.endsWith("9")));

        Optional<Employee> employee = this.employeeRepository.findByName("Parker");
        LOGGER.info("Loaded employee {}", employee.orElse(Employee.UNKNOWN).getName());
    }
}
