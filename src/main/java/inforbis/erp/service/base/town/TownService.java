package inforbis.erp.service.base.town;

import inforbis.erp.model.base.Company;
import inforbis.erp.model.base.Town;

/**
 * Created by dvirovec on 26.9.2016..
 */
public interface TownService {

    Iterable<Town> findAll();
    Town findOne(Long id);
    Iterable<Town> findByCountryCode(String countryCode);
    Iterable<Town> findByName(String name);
    Town create(Town gretting);
    Town update(Town Company);
    void delete(Long id);

}
