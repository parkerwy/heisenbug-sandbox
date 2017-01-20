package lab.heisenbug.sandbox.payroll.domain;

import lab.heisenbug.sandbox.payroll.domain.schedule.PaymentSchedule;
import org.joda.time.DateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: Oct 30, 2010
 * Time: 2:24:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class CommissionedClassification extends PaymentClassification {

    @Digits(integer = 11, fraction = 2)
    @Column(name = "BASE_SALARY")
    private BigDecimal salary;

    @Digits(integer = 0, fraction = 3)
    @Column(name = "COMM_RATE")
    private BigDecimal commissionRate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<SalesReceipt> salesReceipts = new LinkedList<>();

    public CommissionedClassification() {
        super(PaymentSchedule.BIWEEKLY);
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public List<SalesReceipt> getSalesReceipts() {
        return Collections.unmodifiableList(salesReceipts);
    }

    public void addSalesReceipt(SalesReceipt saleReceipt) {
        this.salesReceipts.add(saleReceipt);
        saleReceipt.setOwner(this);
    }

    @Override
    public BigDecimal calculatePay(DateTime date) {
        return salary;
    }
}
