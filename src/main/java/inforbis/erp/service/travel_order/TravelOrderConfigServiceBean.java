package inforbis.erp.service.travel_order;

import inforbis.erp.model.travel_order.TravelOrderConfig;
import inforbis.erp.repository.travel_order.TravelOrderConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Service
public class TravelOrderConfigServiceBean implements TravelOrderConfigService {

    @Autowired
    private TravelOrderConfigRepository travel_order_configRepository;

    @Override
    public Iterable<TravelOrderConfig> findAll() {

        Iterable<TravelOrderConfig> travel_order_configs = travel_order_configRepository.findAll();

        return travel_order_configs;
    }

    @Override
    public TravelOrderConfig findOne(Long id) {

        TravelOrderConfig travel_order_config = travel_order_configRepository.findOne(id);

        return travel_order_config;
    }

    @Override
    public TravelOrderConfig create(TravelOrderConfig travel_order_config) {

        if(travel_order_config.getCompany_id()!=null) {
            return null;
        }

        TravelOrderConfig saved_travel_order_config = travel_order_configRepository.save(travel_order_config);

        return saved_travel_order_config;

    }

    @Override
    public TravelOrderConfig update(TravelOrderConfig travel_order_config) {

        TravelOrderConfig travel_order_configPersisted = findOne(travel_order_config.getCompany_id());
                if(travel_order_configPersisted== null){
                    return null;
                }

        TravelOrderConfig updated_travel_order_config = travel_order_configRepository.save(travel_order_config);

        return updated_travel_order_config;
    }

    @Override
    public void delete(Long id) {
        travel_order_configRepository.delete(id);
    }
}
