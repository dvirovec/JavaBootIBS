package inforbis.erp.model.base;

import org.hibernate.event.spi.LoadEventListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;


/**
 * Created by dvirovec on 29.9.2016..
*/
@Entity
@Table(name="user", schema="base")
public class User extends BaseEntity {

    private String name;

    @Size(max=65)
    private String password;

    private String email;

    private Boolean active;

    @Transient
    private String sessionId;

    @ManyToMany
    @JoinTable(name = "user_role", schema="base", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne
    @JoinColumn(name="department_id")
    private Department department;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="user_details_id")
    private UserDetail userDetails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public UserDetail getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetail userDetails) {
        this.userDetails = userDetails;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
