package dao.collection;

import dao.AccountDao;
import domain.Account;
import domain.Hashtag;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
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

    public void delete(Long id) {
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
}
