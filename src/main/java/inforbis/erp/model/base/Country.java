package inforbis.erp.model.base;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by dvirovec on 29.9.2016..
 */
@Entity
@Table(name="country", schema="base")
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    private String code;
    private String name;
    private Double day_amount;
    private String currency;

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

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public Double getDay_amount() { return day_amount; }

    public void setDay_amount(Double day_amount) { this.day_amount = day_amount; }

    public String getCurrency() { return currency; }

    public void setCurrency(String currency) { this.currency = currency; }
}
