package lab.heisenbug.sandbox.payroll.domain.method;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: Oct 30, 2010
 * Time: 2:34:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PaymentMethod {

    void pay(BigDecimal amount);
}
