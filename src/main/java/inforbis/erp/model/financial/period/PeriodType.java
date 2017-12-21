package inforbis.erp.model.financial.period;

import javax.persistence.*;

/**
 * Created by dvirovec on 2.7.2017..
 */
@Entity
@Table(name="period_type", schema="financial")
public class PeriodType {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
