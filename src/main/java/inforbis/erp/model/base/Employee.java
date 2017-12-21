package inforbis.erp.model.base;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

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

    private Date date_birth;

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

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }
}
