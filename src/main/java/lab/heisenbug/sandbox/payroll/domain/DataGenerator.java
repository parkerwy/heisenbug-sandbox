package lab.heisenbug.sandbox.payroll.domain;

/**
 * Created by parker on 01/02/2017.
 */
public interface DataGenerator<T> {

    T buildFake();

}
