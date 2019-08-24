package inforbis.erp.model.financial.currency;

import inforbis.erp.model.base.BaseEntity;
import inforbis.erp.model.base.Country;

import javax.persistence.*;

/**
 * Created by dvirovec on 23.12.2016..
 */
@Entity
@Table(name="currency", schema="financial")
public class Currency extends BaseEntity {

    private Integer num;
    private String code;
    private String name;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) { this.code = code; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
