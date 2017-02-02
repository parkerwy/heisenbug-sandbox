package lab.heisenbug.sandbox.payroll.domain.method;

import com.github.javafaker.Faker;
import io.codearte.jfairy.Fairy;
import lab.heisenbug.sandbox.payroll.domain.DataGenerator;

/**
 * Created by parker on 01/02/2017.
 */
public enum PaymentMethods implements DataGenerator<BasePaymentMethod> {

    DIRECT {
        @Override
        public BasePaymentMethod buildFake() {
            Faker faker = new Faker();
            Fairy fairy = Fairy.create();
            DirectMethod method = new DirectMethod();
            method.setAccount(faker.finance().creditCard());
            Bank[] banks = Bank.values();
            method.setBank(banks[fairy.baseProducer().randomBetween(0, banks.length - 1)]);
            return method;
        }
    }, HOLD {
        @Override
        public BasePaymentMethod buildFake() {
            return new HoldMethod();
        }
    }, MAIL {
        @Override
        public BasePaymentMethod buildFake() {
            Faker faker = new Faker();
            MailMethod method = new MailMethod();
            method.setAddress(faker.address().streetAddress());
            return method;
        }
    }

}
