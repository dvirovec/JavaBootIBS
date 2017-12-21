package inforbis.erp.service.base.country;

import inforbis.erp.model.base.Country;

/**
 * Created by dvirovec on 26.9.2016..
 */
public interface CountryService {

    Iterable<Country> findAll();
    Country findOne(Long id);
    Country create(Country country);
    Country update(Country country);
    void delete(Long id);

}
