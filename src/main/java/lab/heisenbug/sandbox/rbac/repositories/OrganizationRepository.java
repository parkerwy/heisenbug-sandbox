package lab.heisenbug.sandbox.rbac.repositories;

import lab.heisenbug.sandbox.rbac.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: 5/18/12
 * Time: 9:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface OrganizationRepository extends JpaRepository<Organization, Long>, JpaSpecificationExecutor<Organization> {

    Organization findByName(String name);
}
