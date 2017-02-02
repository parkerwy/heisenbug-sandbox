package lab.heisenbug.sandbox.payroll.domain;

import io.codearte.jfairy.Fairy;

/**
 * Created by parker on 01/02/2017.
 */
public interface DataGenerator<T> {

    T buildFake();

}
