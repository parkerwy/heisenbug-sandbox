package lab.heisenbug.sandbox.payroll.domain.schedule;

import org.joda.time.DateTime;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: Oct 30, 2010
 * Time: 9:59:48 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PaymentSchedule {

    boolean isPayDay(DateTime date);
}
