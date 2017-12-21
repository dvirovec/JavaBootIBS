package inforbis.erp.model.base;

import inforbis.erp.model.financial.currency.Currency;

import javax.persistence.*;

/**
 * Created by dvirovec on 29.9.2016..
 */
@Entity
@Table(name="country", schema="base")
public class Country extends BaseEntity {

    private String code;
    private String name;
    private Double day_amount;

    @ManyToOne
    @JoinColumn(name="currency_id")
    private Currency currency;

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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
