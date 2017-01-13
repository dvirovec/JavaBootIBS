package inforbis.erp.service.financial.currency;

import inforbis.erp.model.financial.currency.Currency;
import inforbis.erp.repository.financial.currency.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Service
public class CurrencyServiceBean implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Iterable<Currency> findAll() {

        Iterable<Currency> currencys = currencyRepository.findAll();

        return currencys;
    }

    @Override
    public Currency findOne(Long id) {

        Currency currency = currencyRepository.findOne(id);

        return currency;
    }

    @Override
    public Currency create(Currency currency) {

        if(currency.getId()!=null) {
            return null;
        }

        Currency savedCurrency = currencyRepository.save(currency);

        return savedCurrency;

    }

    @Override
    public Currency update(Currency currency) {

        Currency currencyPersisted = findOne(currency.getId());
                if(currencyPersisted== null){
                    return null;
                }

        Currency updatedCurrency = currencyRepository.save(currency);

        return updatedCurrency;
    }

    @Override
    public void delete(Long id) {

        currencyRepository.delete(id);

    }
}
