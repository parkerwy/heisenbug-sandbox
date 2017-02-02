package lab.heisenbug.sandbox.payroll.domain.method;

import lab.heisenbug.sandbox.payroll.domain.Employee;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: Nov 28, 2010
 * Time: 9:02:21 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "PAYMENT_METHOD")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CLASS", discriminatorType = DiscriminatorType.STRING, length = 100)
public abstract class BasePaymentMethod implements Serializable, PaymentMethod {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @OneToOne(mappedBy = "paymentMethod")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
