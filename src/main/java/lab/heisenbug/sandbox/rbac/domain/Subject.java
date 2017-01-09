package lab.heisenbug.sandbox.rbac.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: 5/21/11
 * Time: 6:38 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "RBAC_SUBJECTS")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @NotNull
    private String credential;

    @ManyToMany
    @JoinTable(name = "RBAC_SUBJECT_ROLE", joinColumns = @JoinColumn(name = "SUBJECT_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> grantedRoles = new HashSet<Role>();

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public Set<Role> getGrantedRoles() {
        return Collections.unmodifiableSet(grantedRoles);
    }

    public void grantRole(Role role) {
        grantedRoles.add(role);
    }

    public void revokeRole(Role role) {
        grantedRoles.remove(role);
    }
}
