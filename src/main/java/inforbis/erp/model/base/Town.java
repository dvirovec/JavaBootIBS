package inforbis.erp.model.base;

import com.fasterxml.jackson.databind.deser.Deserializers;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by dvirovec on 4.12.2016..
 */
@Entity
@Table(name="town", schema="base")
public class Town extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;

    private String postalCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
