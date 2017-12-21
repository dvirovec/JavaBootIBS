package inforbis.erp.model.base;

import javax.persistence.*;
import java.sql.Date;


/**
 * Created by dvirovec on 29.9.2016..
 */
@Entity
@Table(name="user_detail", schema="base")
public class UserDetail extends BaseEntity {

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String streetAddress;

    private String City;

    private String postalCode;

    private String phoneNumber;

    @OneToOne(mappedBy = "userDetails")
    private User user;

    @OneToOne
    @JoinColumn(name="country_id")
    private Country country;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
