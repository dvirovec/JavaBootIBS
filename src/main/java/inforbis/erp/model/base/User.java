package inforbis.erp.model.base;

import org.hibernate.event.spi.LoadEventListener;

import javax.persistence.*;


/**
 * Created by dvirovec on 29.9.2016..
*/
@Entity
@Table(name="user", schema="base")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String password;

    private String email;

    private Boolean active;

    @OneToOne
    @JoinColumn(name="department_id")
    private Department department;

    @OneToOne
    @JoinColumn(name="user_details_id")
    private UserDetail userDetails;

    public Long getId() {
        return id;
    }

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

    public void setId(Long id) {
        this.id = id;
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
}
