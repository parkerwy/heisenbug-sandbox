package lab.heisenbug.sandbox.payroll.domain.method;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: Oct 30, 2010
 * Time: 3:18:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class MailMethod extends BasePaymentMethod {

    private static final long serialVersionUID = -2314353651581525798L;
    
    @NotNull
    @Column(name = "ADDRESS")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void pay(BigDecimal amount) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
