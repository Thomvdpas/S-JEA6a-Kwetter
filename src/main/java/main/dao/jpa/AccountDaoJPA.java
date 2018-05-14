package main.dao.jpa;

import main.dao.AccountDao;
import main.dao.GenericDaoJPAImpl;
import main.dao.JPA;
import main.domain.Account;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * @author Thom van de Pas on 1-3-2018
 */
@Stateless
@JPA
public class AccountDaoJPA extends GenericDaoJPAImpl<Account> implements AccountDao {

    public AccountDaoJPA() {
    }

    public Account findByUsername(String username) {
        TypedQuery<Account> query =  getEntityManager().createNamedQuery("account.findByUsername", Account.class)
                .setParameter("username", username);

        return oneResult(query);
    }

    @Override
    public Account findByCredentials(String username, String password) {
        TypedQuery<Account> query = getEntityManager().createNamedQuery("account.findByCredentials", Account.class)
                .setParameter("username", username)
                .setParameter("password", password);

        return oneResult(query);
    }
}
