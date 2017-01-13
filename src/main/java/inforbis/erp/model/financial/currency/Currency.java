package inforbis.erp.model.financial.currency;

import inforbis.erp.model.base.Country;

import javax.persistence.*;

/**
 * Created by dvirovec on 23.12.2016..
 */
@Entity
@Table(name="currency", schema="financial")
public class Currency {

    @Id
    @GeneratedValue
    private Long id;
    private Integer currencyNum;
    private String currencyCode;
    private String currencyName;

    @OneToOne
    @JoinColumn(name="country_id")
    private Country country;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCurrencyNum() {
        return currencyNum;
    }

    public void setCurrencyNum(Integer currencyNum) {
        this.currencyNum = currencyNum;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
