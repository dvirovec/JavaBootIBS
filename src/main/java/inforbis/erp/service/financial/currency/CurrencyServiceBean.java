package inforbis.erp.service.financial.currency;

import inforbis.erp.model.financial.currency.Currency;
import inforbis.erp.repository.financial.currency.CurrencyRepository;
import inforbis.erp.service.ErpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Service
public class CurrencyServiceBean extends ErpService implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Iterable<Currency> findAll() {

        Iterable<Currency> currencys = currencyRepository.findAll(getClientId());

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

        currency.setClient_id(getClientId());

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
