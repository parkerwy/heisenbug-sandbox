package lab.heisenbug.sandbox.payroll.domain;

import lab.heisenbug.sandbox.payroll.domain.schedule.PaymentSchedule;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: Oct 30, 2010
 * Time: 2:13:04 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class SalariedClassification extends PaymentClassification {

    @Digits(integer = 11, fraction = 2)
    @Column(name = "FULL_SALARY")
    private BigDecimal salary;

    public SalariedClassification() {
        super(PaymentSchedule.MONTHLY);
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setSalary(String salary) {
        this.salary = new BigDecimal(salary);
    }

    public BigDecimal calculatePay(DateTime date) {
        return salary;
    }
}
