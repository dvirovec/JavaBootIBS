
package inforbis.erp.repository.base;

import inforbis.erp.model.base.Town;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Repository
public interface TownRepository extends CrudRepository<Town, Long> {

    Iterable<Town> findByCountryCode(String countryCode);

    Iterable<Town> findByNameStartingWithIgnoreCase(String name);

    @Query("select a from Town a where a.client_id = :client_id and a.name like :name"+"%" )
    Iterable<Town> findTownByName(@Param("client_id") Long client_id, @Param("name") String name);

    @Query("select a from Town a where a.client_id = :client_id")
    Iterable<Town> findAll(@Param("client_id") Long client_id);



}
