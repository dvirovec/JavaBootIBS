package inforbis.erp.repository.financial.account;

import inforbis.erp.model.base.Company;
import inforbis.erp.model.financial.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by dvirovec on 22.3.2017..
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    //@Query("SELECT a FROM Account a WHERE a.name LIKE CONCAT('%',:name,'%')")
    //Iterable<Account> findAccountByNameSample(@Param("name") String name);
    Iterable<Account> findByNameContaining(String name);

    @Query("SELECT a FROM Account a WHERE a.code LIKE :code%")
    Iterable<Account> findAccountByCodeSample(@Param("code") String code);

}
