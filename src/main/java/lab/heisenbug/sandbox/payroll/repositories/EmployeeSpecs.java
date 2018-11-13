package lab.heisenbug.sandbox.payroll.repositories;

import lab.heisenbug.sandbox.payroll.domain.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

/**
 * Created by parker on 04/02/2017.
 */
public class EmployeeSpecs {

    public static Specification<Employee> ofClassification(final Class<? extends BasePaymentClassification> classification) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Employee_.name)));
            Join<Employee, BasePaymentClassification> join = root.join(Employee_.paymentClassification);
            return criteriaBuilder.equal(join.type(), criteriaBuilder.literal(classification));
        };
    }
}
