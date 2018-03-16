package service;

import dao.AccountDao;
import dao.JPA;
import domain.Account;
import event.AccountEvent;
import interceptor.LoggingInterceptor;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thom van de Pas on 9-3-2018
 */
@Stateless
@Interceptors(LoggingInterceptor.class)
public class AccountService {

    /**
     * Injects the AccountDao and using the @JPA Annotation for the container.
     */
    @Inject
    @JPA
    private AccountDao accountDao;
    @Inject
    private Event<AccountEvent> accountEvent;

    /**
     * Empty constructor for the JPA.
     */
    public AccountService() {
    }

    /**
     * Finds an Account by its username.
     *
     * @param username
     * @returns the Account.
     */
    public Account findByUsername(String username) {
        return this.accountDao.findByUsername(username);
    }

    /**
     * Persists a new Account.
     *
     * @param account
     * @returns the created Account.
     */
    public Account create(Account account) {
        return this.accountDao.create(account);
//        accountEvent.fire(new AccountEvent(account));
    }

    /**
     * Updates a new Account by Merging it in the Dao.
     *
     * @param account
     * @returns the updated Account.
     */
    public Account update(Account account) {
        return this.accountDao.update(account);
    }

    /**
     * Deletes an Account.
     *
     * @param account
     */
    public void delete(Account account) {
        this.accountDao.delete(account);
    }

    /**
     * Deletes an Account using its id.
     *
     * @param id
     */
    public void delete(Long id) {
        this.accountDao.deleteById(id);
    }

    /**
     * Finds an Account with its id.
     *
     * @param id
     * @returns the found Account.
     */
    public Account findById(Long id) {
        return this.accountDao.findById(id);
    }

    /**
     * Finds all the known Accounts.
     *
     * @return
     */
    public List<Account> findAll() {
        return this.accountDao.findAll();
    }

    public List<JsonObject> multipleToJson(List<Account> accounts) {
        List<JsonObject> jsonObjects = new ArrayList<JsonObject>();
        for (Account account : accounts) {
            jsonObjects.add(account.toJson());
        }
        return jsonObjects;
    }

    public void setDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
