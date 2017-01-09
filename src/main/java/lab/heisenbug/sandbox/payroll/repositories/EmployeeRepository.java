package lab.heisenbug.sandbox.payroll.repositories;

import lab.heisenbug.sandbox.payroll.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by parker on 09/01/2017.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Employee findByName(String name);
}
