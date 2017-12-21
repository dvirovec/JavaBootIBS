package inforbis.erp.repository.base;

import inforbis.erp.model.base.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    @Query("select a from Country a where a.client_id = :client_id and a.name like :name"+"%" )
    Iterable<Country> findCountryByName(@Param("client_id") Long client_id, @Param("name") String name);

    @Query("select a from Country a where a.client_id = :client_id")
    Iterable<Country> findAll(@Param("client_id") Long client_id);

}
