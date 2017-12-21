package inforbis.erp.model.travel_order;

import inforbis.erp.model.base.Employee;
import inforbis.erp.model.base.Town;

import inforbis.erp.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by dvirovec on 14.3.2017..
 */
@Entity
@Table(name="order", schema="travel")
public class TravelOrder extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town destination;

    private LocalDate startDate;

    private Integer travelDays;
}
