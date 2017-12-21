package inforbis.erp.service.financial.account;

import inforbis.erp.model.financial.account.Account;
import inforbis.erp.repository.financial.account.AccountRepository;
import inforbis.erp.service.ErpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dvirovec on 22.3.2017..
 */
@Service
public class AccountServiceBean extends ErpService implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Iterable<Account> findAll() {

        Iterable<Account> accounts = accountRepository.findAll(getClientId());

        return accounts;
    }

    @Override
    public Iterable<Account> findAccountByName(String name) {

        Iterable<Account> accounts = accountRepository.findByNameContaining(name);

        return accounts;
    }

    @Override
    public Iterable<Account> findAccountByCode(String code) {


        Iterable<Account> accounts = accountRepository.findAccountByCodeSample(getClientId(),code);
        return accounts;
    }


    @Override
    public Account findOne(Long id) {

        Account account = accountRepository.findOne(id);

        return account;
    }

    @Override
    public Account create(Account account) {

        if(account.getId()!=null) {
            return null;
        }

        account.setClient_id(getClientId());

        Account savedAccount = accountRepository.save(account);

        return savedAccount;
    }

    @Override
    public Account update(Account account) {

        Account accountPersisted = findOne(account.getId());

        if(accountPersisted==null) {
            return null;
        }

        Account updatedAccount = accountRepository.save(account);

        return updatedAccount;
    }

    @Override
    public void delete(Long id) {
        accountRepository.delete(id);
    }
}
