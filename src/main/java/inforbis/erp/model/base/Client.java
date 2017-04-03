package inforbis.erp.model.base;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dvirovec on 27.3.2017..
 */
@Entity
@Table(name="client", schema="base")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String clientName;

    private Boolean active;

    private Date activeUntil;
}
