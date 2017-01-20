package lab.heisenbug.sandbox.payroll.domain.method;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: Oct 30, 2010
 * Time: 3:17:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class DirectMethod extends PaymentMethod {

    @Column(name = "BANK")
    @Enumerated(EnumType.STRING)
    private Bank bank;

    @Column(name = "ACCOUNT")
    private String account;

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public void pay(BigDecimal amount) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
