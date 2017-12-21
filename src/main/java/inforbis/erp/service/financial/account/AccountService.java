package inforbis.erp.service.financial.account;

import inforbis.erp.model.financial.account.Account;
import inforbis.erp.model.financial.currency.Currency;

/**
 * Created by dvirovec on 22.3.2017..
 */
public interface AccountService {

    Iterable<Account> findAll();
    Iterable<Account> findAccountByName(String name);
    Iterable<Account> findAccountByCode(String code);
    Account findOne(Long id);
    Account create(Account account);
    Account update(Account account);
    void delete(Long id);

}
