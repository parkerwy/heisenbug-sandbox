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
 * Time: 2:15:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class HourlyClassification extends PaymentClassification {

    @Digits(integer = 11, fraction = 2)
    @Column(name = "HOUR_RATE")
    private BigDecimal rate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<TimeCard> timeCards = new LinkedList<>();

    public HourlyClassification() {
        super(PaymentSchedule.WEEKLY);
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public List<TimeCard> getTimeCards() {
        return Collections.unmodifiableList(timeCards);
    }

    public void addTimeCard(TimeCard timeCard) {
        this.timeCards.add(timeCard);
    }

    @Override
    public BigDecimal calculatePay(DateTime date) {
        return rate;
    }
}
