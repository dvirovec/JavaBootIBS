package inforbis.erp.model.financial.posting;

import inforbis.erp.model.base.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dvirovec on 21.6.2017..
 */
@Entity
@Table(name="account_posting", schema="financial")
public class Posting extends BaseEntity {

    @OneToMany
    @JoinColumn(name="posting_id", referencedColumnName="id")
    private List<PostingItem> items;

    private Date posting_date;

    private String description;

    public List<PostingItem> getItems() {
        return items;
    }

    public void setItems(List<PostingItem> items) {
        this.items = items;
    }

    public Date getPosting_date() {
        return posting_date;
    }

    public void setPosting_date(Date posting_date) { this.posting_date = posting_date; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Posting() {
        super();
        this.posting_date = new Date(Calendar.getInstance().getTimeInMillis());
        this.description="Opis knjiženenja";
    }


}
