package inforbis.erp.model.base;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dvirovec on 2.4.2017..
 */
@Entity
@Table(name = "role", schema="base")
public class Role {

    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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



}