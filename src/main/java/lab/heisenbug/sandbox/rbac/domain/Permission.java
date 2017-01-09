package lab.heisenbug.sandbox.rbac.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: 5/21/11
 * Time: 6:45 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "RBAC_PERMISSIONS")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @Column(name = "RESOURCE", nullable = false)
    @NotNull
    private String resource;

    @Column(name = "OPERATION", nullable = false)
    @NotNull
    private String operation;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
