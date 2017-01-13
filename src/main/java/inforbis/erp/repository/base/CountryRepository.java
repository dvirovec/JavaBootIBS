package inforbis.erp.repository.base;

import inforbis.erp.model.base.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {


}
