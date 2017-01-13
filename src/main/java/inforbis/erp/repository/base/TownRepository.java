
package inforbis.erp.repository.base;

import inforbis.erp.model.base.Town;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Repository
public interface TownRepository extends CrudRepository<Town, Long> {

    Iterable<Town> findByCountryCode(String countryCode);

    Iterable<Town> findByNameStartingWithIgnoreCase(String name);

}
