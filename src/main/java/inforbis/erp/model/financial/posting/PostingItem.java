package inforbis.erp.model.financial.posting;

import inforbis.erp.model.base.BaseEntity;
import inforbis.erp.model.financial.account.Account;

import javax.persistence.*;

/**
 * Created by dvirovec on 21.6.2017..
 */
@Entity
@Table(name="posting_item", schema="financial")
public class PostingItem extends BaseEntity {

    @Column(name="posting_id")
    private Long postingId;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account acount;

    private Double debit;
    private Double credit;


    public Long getPostingId() {
        return postingId;
    }

    public void setPostingId(Long postingId) {
        this.postingId = postingId;
    }

    public Account getAcount() {
        return acount;
    }

    public void setAcount(Account acount) {
        this.acount = acount;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}
