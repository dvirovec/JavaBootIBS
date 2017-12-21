package inforbis.erp.model.base;

import com.fasterxml.jackson.databind.deser.Deserializers;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by dvirovec on 4.12.2016..
 */
@Entity
@Table(name="town", schema="base")
public class Town extends BaseEntity {

    private String name;

    private String countryCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
