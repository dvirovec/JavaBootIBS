package inforbis.erp.service.financial.period;

import inforbis.erp.model.financial.period.AccountPeriod;
import inforbis.erp.model.financial.period.PeriodType;
import inforbis.erp.repository.financial.period.AccountPeriodRepository;
import inforbis.erp.repository.financial.period.PeriodTypeRepository;
import inforbis.erp.service.ErpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dvirovec on 26.9.2016..
 */
@Service
public class PeriodServiceBean extends ErpService implements PeriodService {

    @Autowired
    private AccountPeriodRepository periodRepository;

    @Autowired
    private PeriodTypeRepository periodTypeRepository;

    @Override
    public Iterable<AccountPeriod> findAll() {

        Iterable<AccountPeriod> periods = periodRepository.findAll(getClientId());

        return periods;
    }

    @Override
    public AccountPeriod findOne(Long id) {

        AccountPeriod period = periodRepository.findOne(id);

        return period;
    }

    @Override
    public AccountPeriod create(AccountPeriod period) {

        if(period.getId()!=null) {
            return null;
        }

        period.setClient_id(getClientId());

        AccountPeriod savedPeriod = periodRepository.save(period);

        return savedPeriod;

    }

    @Override
    public AccountPeriod update(AccountPeriod period) {

        AccountPeriod periodPersisted = findOne(period.getId());
                if(periodPersisted== null){
                    return null;
                }

        AccountPeriod updatedPeriod = periodRepository.save(period);

        return updatedPeriod;
    }

    @Override
    public void delete(Long id) {

        periodRepository.delete(id);

    }

    @Override
    public Iterable<PeriodType> findPeriodTypeByCode(String code) {
        return null;
    }
}
