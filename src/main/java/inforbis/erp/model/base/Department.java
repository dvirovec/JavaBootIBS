package inforbis.erp.model.base;

import javax.persistence.*;

/**
 * Created by dvirovec on 27.9.2016..
 */
@Entity
@Table(name="department", schema="base")
public class Department extends BaseEntity {

    private String name;
    private String address;

    @ManyToOne
    @JoinColumn(name="parent_dep_id")
    private Department department;

    @ManyToOne(optional=true)
    @JoinColumn(name="company_id")
    private Company company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
