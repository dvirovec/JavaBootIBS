package inforbis.erp.repository.base;

import inforbis.erp.model.base.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    /*@Query("select a from Company a where a.name like :companyName"+"% and client_id = :client_id" )
    Iterable<Company> findCompanyByName(@Param("companyName") String companyName, @Param("client_id") Long client_id);
*/

    @Query("select a from Company a where a.name like :companyName"+"%" )
    Iterable<Company> findCompanyByName(@Param("companyName") String companyName);

    @Query("select a from Company a where client_id = :client_id")
    Iterable<Company> findAll(@Param("client_id") Long client_id);

}
