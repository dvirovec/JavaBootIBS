package inforbis.erp.controller.financial.account;

import inforbis.erp.model.financial.account.Account;
import inforbis.erp.service.financial.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

/**
 * Created by dvirovec on 22.3.2017..
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value="erp/account", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Account>> getAccounts() {

        Iterable<Account> accounts = accountService.findAll();

        return new ResponseEntity<Iterable<Account>>(accounts, HttpStatus.OK);

    }

    @RequestMapping(value="/erp/account/{id}", method = RequestMethod.GET,
                                               produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccount(@PathVariable("id") Long id) {

        if(id==-1)
            return new ResponseEntity<Account>(new Account(), HttpStatus.OK);

        Account account = accountService.findOne(id);

        if(account==null) {
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/erp/account", method = RequestMethod.POST,
                                             consumes = MediaType.APPLICATION_JSON_VALUE,
                                             produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> createAccount (@RequestBody Account account){

            Account savedAccount = accountService.create(account);

            return new ResponseEntity<Account>(savedAccount, HttpStatus.CREATED);
        }

    @RequestMapping(value="/erp/account/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
                                               produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> updateAccount(@PathVariable("id") Long id, @RequestBody Account account  ){

        Account updatedAccount = accountService.update(account);

        if(updatedAccount==null) {
            return new ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Account>(updatedAccount, HttpStatus.OK);

    }

    @RequestMapping(value="/erp/account/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> deleteAccount(@PathVariable("id") Long id, @RequestBody Account account) {

        accountService.delete(id);

        return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(value="/erp/account/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Account>> getAccountByName(@PathVariable("name") String name) {

        Iterable<Account> accounts = accountService.findAccountByName(name);

        return new ResponseEntity<Iterable<Account>>(accounts, HttpStatus.OK);

    }

    @RequestMapping(value="/erp/account/code/{code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Account>> getAccountByExample(@PathVariable("code") String code) {

        Iterable<Account> accounts = accountService.findAccountByCode(code);

        return new ResponseEntity<Iterable<Account>>(accounts, HttpStatus.OK);

    }




}
