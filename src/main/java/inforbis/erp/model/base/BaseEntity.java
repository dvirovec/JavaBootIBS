package inforbis.erp.model.base;

import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.Calendar;

/**
 * Created by dvirovec on 14.3.2017..
 */

@MappedSuperclass
public abstract class BaseEntity {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="client_id")
    private Long client_id;

    private Timestamp created;

    private Timestamp updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    @PrePersist
    void preInsert() {
        created = new Timestamp(Calendar.getInstance().getTimeInMillis());
        updated = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    @PreUpdate
    void preUpdate() {
        updated = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

}
