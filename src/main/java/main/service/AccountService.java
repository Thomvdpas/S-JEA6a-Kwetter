package main.service;

import com.mysql.jdbc.StringUtils;
import main.dao.AccountDao;
import main.dao.JPA;
import main.domain.Account;
import main.event.AccountEvent;
import main.interceptor.LoggingInterceptor;
import main.util.EncryptionHelper;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

import static main.util.HelperFunctions.isNull;

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
        if (!StringUtils.isNullOrEmpty(username)) {
            return this.accountDao.findByUsername(username);
        }
        return null;
    }

    /**
     * Persists a new Account.
     *
     * @param account
     * @returns the created Account.
     */
    public Account create(Account account) {
        if (!isNull(account)) {
            return this.accountDao.create(account);
//            accountEvent.fire(new AccountEvent(account));
        }
        return null;
    }

    /**
     * Updates a new Account by Merging it in the Dao.
     *
     * @param account
     * @returns the updated Account.
     */
    public Account update(Account account) {
        if (!isNull(account)) {
            return this.accountDao.update(account);
        }
        return null;
    }

    /**
     * Deletes an Account.
     *
     * @param account
     */
    public void delete(Account account) {
        if (!isNull(account)) {
            this.accountDao.delete(account);
        }
    }

    /**
     * Deletes an Account using its id.
     *
     * @param id
     */
    public void delete(Long id) {
        if (!isNull(id)) {
            this.accountDao.deleteById(id);
        }
    }

    /**
     * Finds an Account with its id.
     *
     * @param id
     * @returns the found Account.
     */
    public Account findById(Long id) {
        if (!isNull(id)) {
            return this.accountDao.findById(id);
        }
        return null;
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

    public Account findByCredentials(String username, String password) {
        return this.accountDao.findByCredentials(username, EncryptionHelper.encryptData(password));
    }
}
