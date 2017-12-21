package inforbis.erp.service.financial.period;

import inforbis.erp.model.financial.period.AccountPeriod;
import inforbis.erp.model.financial.period.PeriodType;

/**
 * Created by dvirovec on 26.9.2016..
 */
public interface PeriodService {

    Iterable<AccountPeriod> findAll();
    AccountPeriod findOne(Long id);
    AccountPeriod create(AccountPeriod period);
    AccountPeriod update(AccountPeriod period);
    void delete(Long id);

    Iterable<PeriodType> findPeriodTypeByCode(String code);

}
