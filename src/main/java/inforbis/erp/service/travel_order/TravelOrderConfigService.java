package inforbis.erp.service.travel_order;

import inforbis.erp.model.base.Company;
import inforbis.erp.model.travel_order.TravelOrderConfig;

/**
 * Created by dvirovec on 26.9.2016..
 */
public interface TravelOrderConfigService {

    Iterable<TravelOrderConfig> findAll();
    TravelOrderConfig findOne(Long id);
    TravelOrderConfig create(TravelOrderConfig gretting);
    TravelOrderConfig update(TravelOrderConfig Company);
    void delete(Long id);

}
