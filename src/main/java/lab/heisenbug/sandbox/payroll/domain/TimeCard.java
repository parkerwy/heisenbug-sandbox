package lab.heisenbug.sandbox.payroll.domain;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: Oct 31, 2010
 * Time: 10:29:55 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TIME_CARDS")
public class TimeCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private HourlyClassification owner;

    @Column(name = "EFFECTIVE_DATE")
    private DateTime date;

    @Column(name = "HOURS")
    private int hours;

    public HourlyClassification getOwner() {
        return owner;
    }

    public void setOwner(HourlyClassification owner) {
        this.owner = owner;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
