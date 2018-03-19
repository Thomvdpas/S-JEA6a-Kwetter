package main.dao.collection;

import main.dao.AccountDao;
import main.domain.Account;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Thom van de Pas on 1-3-2018
 */
@Stateless
@Default
public class AccountDaoColl implements AccountDao {

    CopyOnWriteArrayList<Account> accounts = new CopyOnWriteArrayList<Account>();

    public Account create(Account account) {
        accounts.add(account);
        return account;
    }

    public Account update(Account account) {
        return null;
    }


    public void delete(Account account) {
        for (Account foundAccount : accounts) {
            if (foundAccount.equals(account)) {
                accounts.remove(account);
            }
        }
    }

    public void deleteById(Long id) {
        Account account = findById(id);
        if (account != null) {
            accounts.remove(account);
        }
    }

    public Account findById(Long id) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    public List<Account> findAll() {
        return accounts;
    }

    public Account findByUsername(String username) {
        return null;
    }

    public Account findByCredentials(String username, String s) {
        return null;
    }
}
