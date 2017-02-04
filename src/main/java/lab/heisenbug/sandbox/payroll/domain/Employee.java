package lab.heisenbug.sandbox.payroll.domain;

import lab.heisenbug.sandbox.payroll.domain.method.BasePaymentMethod;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: Oct 27, 2010
 * Time: 11:46:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "EMPLOYEES")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE")
    private String phone;

    @NotNull
    @Valid
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PAYMENT_CLASS_ID")
    private BasePaymentClassification paymentClassification;

    @NotNull
    @Valid
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PAYMENT_METHOD_ID")
    private BasePaymentMethod paymentMethod;

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String mobilePhone) {
        this.phone = mobilePhone;
    }

    public BasePaymentClassification getPaymentClassification() {
        return paymentClassification;
    }

    public void setPaymentClassification(BasePaymentClassification paymentClassification) {
        paymentClassification.setEmployee(this);
        this.paymentClassification = paymentClassification;
    }

    public BasePaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(BasePaymentMethod paymentMethod) {
        paymentMethod.setEmployee(this);
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("phone", phone)
                .toString();
    }
}
