package inforbis.erp.model.financial.period;

import inforbis.erp.model.base.BaseEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by dvirovec on 1.7.2017..
 */
@Entity
@Table(name="account_period", schema="financial")
public class AccountPeriod extends BaseEntity {

    private String periodDescription;
    private Date startDate;
    private Date finishDate;
    private Boolean periodActive;

    @ManyToOne
    @JoinColumn(name="period_type_id")
    private PeriodType period_type;

    public String getPeriodDescription() {
        return periodDescription;
    }

    public void setPeriodDescription(String periodDescription) {
        this.periodDescription = periodDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Boolean getPeriodActive() {
        return periodActive;
    }

    public void setPeriodActive(Boolean periodActive) {
        this.periodActive = periodActive;
    }

    public PeriodType getPeriod_type() {
        return period_type;
    }

    public void setPeriod_type(PeriodType period_type) {
        this.period_type = period_type;
    }

    public  AccountPeriod() {

        this.periodActive = false;
        this.periodDescription = "";
        this.startDate = new Date(Calendar.getInstance().getTimeInMillis());
        this.finishDate = new Date(Calendar.getInstance().getTimeInMillis());

    }


}

