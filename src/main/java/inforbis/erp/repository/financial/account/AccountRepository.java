package inforbis.erp.repository.financial.account;

import inforbis.erp.model.base.Company;
import inforbis.erp.model.base.Country;
import inforbis.erp.model.financial.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by dvirovec on 22.3.2017..
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Iterable<Account> findByNameContaining(String name);

    @Query("SELECT a FROM Account a WHERE a.client_id = :client_id and a.code LIKE :code%")
    Iterable<Account> findAccountByCodeSample(@Param("client_id") Long client_id, @Param("code") String code);

    @Query("select a from Account a where a.client_id = :client_id and a.name like :name"+"%" )
    Iterable<Account> findAccountByName(@Param("client_id") Long client_id, @Param("name") String name);

    @Query("select a from Account a where a.client_id = :client_id")
    Iterable<Account> findAll(@Param("client_id") Long client_id);


}
