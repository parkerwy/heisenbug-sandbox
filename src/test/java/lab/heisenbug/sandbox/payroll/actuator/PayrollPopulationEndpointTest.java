package lab.heisenbug.sandbox.payroll.actuator;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

import lab.heisenbug.sandbox.SandboxApplicationTest;
import lab.heisenbug.sandbox.payroll.domain.CommissionedClassification;
import lab.heisenbug.sandbox.payroll.domain.Employee;
import lab.heisenbug.sandbox.payroll.domain.HourlyClassification;
import lab.heisenbug.sandbox.payroll.domain.QEmployee;
import lab.heisenbug.sandbox.payroll.domain.SalariedClassification;
import lab.heisenbug.sandbox.payroll.repositories.EmployeeRepository;
import lab.heisenbug.sandbox.payroll.repositories.EmployeeSpecs;
import lab.heisenbug.sandbox.payroll.repositories.ReactiveRepository;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Created by parker on 20/01/2017.
 */
@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
public class PayrollPopulationEndpointTest extends SandboxApplicationTest {

        private static final Logger LOGGER = LoggerFactory.getLogger(PayrollPopulationEndpointTest.class);

        @Autowired
        private PayrollPopulationEndpoint payrollPopulationEndpoint;

        @Autowired
        private EmployeeRepository employeeRepository;

        @Before
        public void setup() {
                this.payrollPopulationEndpoint.populate();
        }

        @Test
        public void shouldQueryWithJPASpecification() throws Exception {

                List<Employee> employeeList = this.employeeRepository
                                .findAll(EmployeeSpecs.ofClassification(CommissionedClassification.class));
                Assertions.assertThat(employeeList).isNotEmpty();
                employeeList.forEach(employee -> LOGGER.info("loaded employee [{}] with Commissioned Classification.",
                                employee.getName()));

                employeeList = this.employeeRepository
                                .findAll(EmployeeSpecs.ofClassification(HourlyClassification.class));
                Assertions.assertThat(employeeList).isNotEmpty();
                employeeList.forEach(employee -> LOGGER.info("loaded employee [{}] with Hourly Classification.",
                                employee.getName()));

                employeeList = this.employeeRepository
                                .findAll(EmployeeSpecs.ofClassification(SalariedClassification.class));
                Assertions.assertThat(employeeList).isNotEmpty();
                employeeList.forEach(employee -> LOGGER.info("loaded employee [{}] with Salaried Classification.",
                                employee.getName()));

        }

        @Test
        public void shouldQueryWithQueryDSLAPI() throws Exception {
                this.employeeRepository.findAll(
                                QEmployee.employee.name.startsWith("P").and(QEmployee.employee.phone.endsWith("9")));

                Optional<Employee> employee = this.employeeRepository.findByName("Parker");
                LOGGER.info("Loaded employee {}", employee.orElse(Employee.UNKNOWN).getName());
        }

        @Test
        public void shouldQueryWithReactiveAPI() throws Exception {
                Flux<Employee> employees = ReactiveRepository.createFlux(() -> employeeRepository.findAll())
                                .publishOn(Schedulers.elastic()).log();
                employees.subscribe();
        }
}
