package inforbis.erp.repository.financial.period;

import inforbis.erp.model.financial.period.AccountPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by dvirovec on 1.7.2017..
 */
public interface AccountPeriodRepository extends JpaRepository<AccountPeriod, Long> {

    @Query("select a from AccountPeriod a where a.client_id = :client_id and a.periodDescription like :name"+"%" )
    Iterable<AccountPeriod> findPeriodByDescription(@Param("client_id") Long client_id, @Param("name") String name);

    @Query("select a from AccountPeriod a where a.client_id = :client_id")
    Iterable<AccountPeriod> findAll(@Param("client_id") Long client_id);

}
