package lab.heisenbug.sandbox.payroll.domain;

import lab.heisenbug.sandbox.payroll.domain.schedule.PaymentSchedules;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: Oct 30, 2010
 * Time: 2:18:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "PAYMENT_CLASS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CLASS", discriminatorType = DiscriminatorType.STRING, length = 100)
public abstract class BasePaymentClassification implements Serializable, PaymentClassification {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @OneToOne(mappedBy = "paymentClassification")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "SCHEDULE")
    private PaymentSchedules schedule;

    protected BasePaymentClassification() {

    }

    protected BasePaymentClassification(PaymentSchedules schedule) {
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean isPayDay(DateTime date) {
        return schedule.isPayDay(date);
    }

}
