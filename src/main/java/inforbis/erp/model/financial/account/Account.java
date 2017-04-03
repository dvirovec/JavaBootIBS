package inforbis.erp.model.financial.account;
import inforbis.erp.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="account", schema="financial")
public class Account extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="parent_id")
    private Account parent;

    private String code;
    private String name;
    private Boolean active;

    public Account getParent() { return parent;}

    public void setParent(Account parent) {
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() { return active; }

    public void setActive(Boolean active) { this.active = active; }
}

