package inforbis.erp.repository.base;

import inforbis.erp.model.base.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    @Query("select a from Company a where a.client_id = :client_id and a.name like :companyName"+"%" )
    Iterable<Company> findCompanyByName(@Param("client_id") Long client_id, @Param("companyName") String companyName);

    @Query("select a from Company a where a.client_id = :client_id")
    Iterable<Company> findAll(@Param("client_id") Long client_id);

}
