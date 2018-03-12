package service;

import dao.AccountDao;
import dao.JPA;
import domain.Account;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Thom van de Pas on 9-3-2018
 */
@Stateless
public class AccountService {

    /**
     * Injects the AccountDao and using the @JPA Annotation for the container.
     */
    @Inject
    @JPA
    private AccountDao accountDao;

    /**
     * Empty constructor for the JPA.
     */
    public AccountService() {
    }

    /**
     * Persists a new Account.
     *
     * @param account
     * @returns the created Account.
     */
    public Account create(Account account) {
        return this.accountDao.create(account);
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
}
