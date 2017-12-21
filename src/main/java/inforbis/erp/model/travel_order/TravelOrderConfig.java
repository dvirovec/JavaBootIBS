package inforbis.erp.model.travel_order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by dvirovec on 22.12.2016..
 */
@Entity
@Table(name="config", schema="travel")
public class TravelOrderConfig {

    @Id
    private Long company_id;

    private Float car_km;

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public Float getCar_km() {
        return car_km;
    }

    public void setCar_km(Float car_km) {
        this.car_km = car_km;
    }
}
