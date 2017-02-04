package lab.heisenbug.sandbox.payroll.repositories;

import lab.heisenbug.sandbox.payroll.domain.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by parker on 04/02/2017.
 */
public class EmployeeSpecs {

    public static Specification<Employee> ofCommissionedClassification() {
        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Employee_.name)));
                Root<CommissionedClassification> from = criteriaQuery.from(CommissionedClassification.class);
                return criteriaBuilder.equal(root, from);
            }
        };
    }

    public static Specification<Employee> ofHourlyClassification() {
        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Employee_.name)));
                Root<HourlyClassification> from = criteriaQuery.from(HourlyClassification.class);
                return criteriaBuilder.equal(root, from);
            }
        };
    }

    public static Specification<Employee> ofSalariedClassification() {
        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Employee_.name)));
                Root<SalariedClassification> from = criteriaQuery.from(SalariedClassification.class);
                return criteriaBuilder.equal(root, from);
            }
        };
    }
}
