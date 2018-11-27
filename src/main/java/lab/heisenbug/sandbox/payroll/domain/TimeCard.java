package lab.heisenbug.sandbox.payroll.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by IntelliJ IDEA. Subject: parker Date: Oct 31, 2010 Time: 10:29:55
 * AM To change this template use File | Settings | File Templates.
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
    @JsonIgnore
    private HourlyClassification owner;

    @Column(name = "EFFECTIVE_DATE")
    @Temporal(TemporalType.DATE)
    private Calendar date;

    @Column(name = "HOURS")
    private int hours;

    public HourlyClassification getOwner() {
        return owner;
    }

    public void setOwner(HourlyClassification owner) {
        this.owner = owner;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
