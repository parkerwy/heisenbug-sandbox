package lab.heisenbug.sandbox.payroll.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: Oct 31, 2010
 * Time: 10:30:26 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SALES_RECEIPTS")
public class SalesReceipt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private CommissionedClassification owner;

    @Column(name = "EFFECTIVE_DATE")
    @Temporal(TemporalType.DATE)
    private Calendar date;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public CommissionedClassification getOwner() {
        return owner;
    }

    public void setOwner(CommissionedClassification owner) {
        this.owner = owner;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
