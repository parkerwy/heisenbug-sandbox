package lab.heisenbug.sandbox.payroll.actuator;

import com.github.javafaker.Faker;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;
import lab.heisenbug.sandbox.payroll.domain.*;
import lab.heisenbug.sandbox.payroll.domain.method.BasePaymentMethod;
import lab.heisenbug.sandbox.payroll.domain.method.PaymentMethods;
import lab.heisenbug.sandbox.payroll.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractExposableEndpoint;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.WebOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by parker on 19/01/2017.
 */
@Controller
@WebEndpoint(id = "payrollPopulation")
@ConfigurationProperties(prefix = "payroll.population")
public class PayrollPopulationEndpoint{

    private final EmployeeRepository employeeRepository;

    private Fairy fairy = Fairy.create();

    @Autowired
    public PayrollPopulationEndpoint(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @WriteOperation
    public String populate() {
        this.employeeRepository.deleteAll();
        this.populateEmployee();
        return "Done";
    }


    private void populateEmployee() {
        IntStream.rangeClosed(1, 50).boxed().collect(Collectors.toList()).forEach(
                (i) -> Arrays.asList(PaymentMethods.values()).forEach(
                        generator -> {
                            {
                                Employee employee = buildEmployee();
                                CommissionedClassification comm = buildCommissionedClassification();
                                employee.setPaymentClassification(comm);
                                employee.setPaymentMethod(generator.buildFake());
                                this.employeeRepository.save(employee);
                            }
                            {
                                Employee employee = buildEmployee();
                                SalariedClassification salar = buildSalariedClassification();
                                employee.setPaymentClassification(salar);
                                employee.setPaymentMethod(generator.buildFake());
                                this.employeeRepository.save(employee);
                            }
                            {
                                Employee employee = buildEmployee();
                                HourlyClassification hourly = buildHourlyClassification();
                                employee.setPaymentClassification(hourly);
                                employee.setPaymentMethod(generator.buildFake());
                                this.employeeRepository.save(employee);
                            }
                        }
                )
        );
    }

    private Employee buildEmployee() {
        Person person = fairy.person(PersonProperties.ageBetween(25, 60));
        Employee employee = new Employee();
        employee.setName(person.getFullName());
        employee.setPhone(person.getTelephoneNumber());
        return employee;
    }

    private CommissionedClassification buildCommissionedClassification() {

        CommissionedClassification comm = new CommissionedClassification();
        comm.setSalary(new BigDecimal(fairy.baseProducer().randomBetween(2000, 5000)));
        comm.setCommissionRate(BigDecimal.valueOf(fairy.baseProducer().randomBetween(0.030, 0.100)).setScale(3, RoundingMode.HALF_UP));

        int numberOfReceipt = fairy.baseProducer().randomBetween(1, 20);
        IntStream.rangeClosed(1, numberOfReceipt).forEach(
                (i) -> {
                    SalesReceipt receipt = new SalesReceipt();
                    receipt.setDate(fairy.dateProducer().randomDateInThePast(1).toGregorianCalendar());
                    receipt.setAmount(new BigDecimal(fairy.baseProducer().randomBetween(200, 5000)));
                    comm.addSalesReceipt(receipt);
                }
        );
        return comm;
    }

    private SalariedClassification buildSalariedClassification() {
        SalariedClassification salar = new SalariedClassification();
        salar.setSalary(new BigDecimal(fairy.baseProducer().randomBetween(2000, 10000)));
        return salar;
    }

    private HourlyClassification buildHourlyClassification() {
        HourlyClassification hourly = new HourlyClassification();
        hourly.setRate(new BigDecimal(fairy.baseProducer().randomBetween(10, 20)));
        int numberOfTimeCard = fairy.baseProducer().randomBetween(1, 20);
        IntStream.rangeClosed(1, numberOfTimeCard).forEach(
                (i) -> {
                    TimeCard timeCard = new TimeCard();
                    timeCard.setDate(fairy.dateProducer().randomDateInThePast(1).toGregorianCalendar());
                    timeCard.setHours(fairy.baseProducer().randomBetween(1, 8));
                    hourly.addTimeCard(timeCard);
                }
        );
        return hourly;
    }
}
