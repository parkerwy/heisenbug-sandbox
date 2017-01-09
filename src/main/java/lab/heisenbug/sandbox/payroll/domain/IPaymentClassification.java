package lab.heisenbug.sandbox.payroll.domain;

import lab.heisenbug.sandbox.payroll.domain.schedule.IPaymentSchedule;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: Oct 30, 2010
 * Time: 12:46:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IPaymentClassification extends IPaymentSchedule {

    BigDecimal calculatePay(DateTime date);
}
