package inforbis.erp.service.financial.currency;

import inforbis.erp.model.financial.currency.Currency;

/**
 * Created by dvirovec on 26.9.2016..
 */
public interface CurrencyService {

    Iterable<Currency> findAll();
    Currency findOne(Long id);
    Currency create(Currency gretting);
    Currency update(Currency currency);
    void delete(Long id);

}
