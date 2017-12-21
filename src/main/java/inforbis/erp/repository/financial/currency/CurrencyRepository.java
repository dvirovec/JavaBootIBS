package inforbis.erp.repository.financial.currency;

import inforbis.erp.model.financial.currency.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by dvirovec on 23.12.2016..
 */
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Query("select a from Currency a where a.client_id = :client_id and a.currencyName like :name"+"%" )
    Iterable<Currency> findCurrencyByName(@Param("client_id") Long client_id, @Param("name") String name);

    @Query("select a from Currency a where a.client_id = :client_id")
    Iterable<Currency> findAll(@Param("client_id") Long client_id);

}
