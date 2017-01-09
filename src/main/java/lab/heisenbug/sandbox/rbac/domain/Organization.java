package lab.heisenbug.sandbox.rbac.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Subject: parker
 * Date: 5/21/11
 * Time: 9:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "RBAC_ORGS")
@NamedQueries({
        @NamedQuery(name = Organization.FIND_BY_NAME, query = "Select org from Organization org where org.name = :name ")
})
@XmlRootElement(name = "organization")
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_BY_NAME = "Organization.findByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;

    @Column(nullable = false, unique = true)
    @NotNull
    private String name;

    private String description;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
