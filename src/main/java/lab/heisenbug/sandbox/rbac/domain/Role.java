package lab.heisenbug.sandbox.rbac.domain;



import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: 5/21/11
 * Time: 6:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "RBAC_ROLES")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @NotNull
    private String name;

    @ManyToOne
    @NotNull
    private Organization organization;

    @ManyToOne()
    @JoinColumn(name = "PARENT_ROLE_ID")
    private Role parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Role> subRoles = new LinkedHashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Role getParent() {
        return parent;
    }

    public void setParent(Role parent) {
        this.parent = parent;
    }

    public Set<Role> getSubRoles() {
        return Collections.unmodifiableSet(subRoles);
    }

    public boolean addSubRole(Role role) {
        Validate.notNull(role.getParent());
        boolean added = subRoles.add(role);
        if (added) {
            role.setParent(this);
        }
        return added;
    }


    public boolean removeSubRole(Role role) {
        Validate.isTrue(this.equals(role.getParent()));
        boolean removed = subRoles.remove(role);
        if (removed) {
            role.setParent(null);
        }
        return removed;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Role rhs = (Role) obj;
        return new EqualsBuilder().append(name, rhs.name).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).toHashCode();
    }
}
