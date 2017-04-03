package inforbis.erp.repository.financial.currency;

import inforbis.erp.model.financial.currency.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dvirovec on 23.12.2016..
 */
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}
