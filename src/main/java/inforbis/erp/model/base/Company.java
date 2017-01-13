package inforbis.erp.model.base;

import javax.persistence.*;

/**
 * Created by dvirovec on 27.9.2016..
 */
@Entity
@Table(name="company", schema="base")
public class Company {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private Long town_id;
    private String CID;

    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public Long getTown_id() {return town_id;}

    public void setTown_id(Long town_id) {this.town_id = town_id;}

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
