package lab.heisenbug.sandbox.payroll.domain.method;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: Oct 30, 2010
 * Time: 3:17:25 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class HoldMethod extends BasePaymentMethod {

    private static final long serialVersionUID = -7704323025336729386L;

    @Override
    public void pay(BigDecimal amount) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
