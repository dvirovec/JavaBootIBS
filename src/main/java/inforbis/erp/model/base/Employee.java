package inforbis.erp.model.base;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by dvirovec on 4.12.2016..
 */
@Entity
@Table(name="employee", schema="hr")
public class Employee extends BaseEntity {

    private String name;

    private String surname;

    private Double salary;

    private String pid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPid() { return pid; }

    public void setPid(String pid) { this.pid = pid; }
}
