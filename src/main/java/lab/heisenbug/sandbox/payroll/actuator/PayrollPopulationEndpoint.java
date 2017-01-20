package lab.heisenbug.sandbox.payroll.actuator;

import lab.heisenbug.sandbox.payroll.domain.CommissionedClassification;
import lab.heisenbug.sandbox.payroll.domain.Employee;
import lab.heisenbug.sandbox.payroll.domain.SalesReceipt;
import lab.heisenbug.sandbox.payroll.domain.method.Bank;
import lab.heisenbug.sandbox.payroll.domain.method.DirectMethod;
import lab.heisenbug.sandbox.payroll.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by parker on 19/01/2017.
 */
@Controller
@ConfigurationProperties(prefix = "payroll.population")
public class PayrollPopulationEndpoint extends AbstractEndpoint<String> {


    private final EmployeeRepository employeeRepository;

    @Autowired
    public PayrollPopulationEndpoint(EmployeeRepository employeeRepository) {
        super("payrollPopulation");
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String invoke() {
        this.employeeRepository.deleteAll();
        this.populateEmployees();
        return "Done";
    }


    private void populateEmployees() {
        Employee employee = new Employee();
        employee.setName("Dominique B. Yocom");
        employee.setPhone("+1(030)-6664177");


        CommissionedClassification comm = new CommissionedClassification();
        comm.setSalary(new BigDecimal("5000"));
        comm.setCommissionRate(new BigDecimal("0.051"));
        employee.setPaymentClassification(comm);

        SalesReceipt sr1 = new SalesReceipt();
        sr1.setDate(Calendar.getInstance());
        sr1.setAmount(new BigDecimal("1000"));
        comm.addSalesReceipt(sr1);

        SalesReceipt sr2 = new SalesReceipt();
        sr2.setDate(Calendar.getInstance());
        sr2.setAmount(new BigDecimal("500"));
        comm.addSalesReceipt(sr2);

        DirectMethod method = new DirectMethod();
        method.setAccount("373702462614710");
        method.setBank(Bank.BOC);
        employee.setPaymentMethod(method);

        this.employeeRepository.save(employee);
    }
}
