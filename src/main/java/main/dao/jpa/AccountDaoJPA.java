package main.dao.jpa;

import main.dao.AccountDao;
import main.dao.GenericDaoJPAImpl;
import main.dao.JPA;
import main.domain.Account;

import javax.ejb.Stateless;

/**
 * @author Thom van de Pas on 1-3-2018
 */
@Stateless
@JPA
public class AccountDaoJPA extends GenericDaoJPAImpl<Account> implements AccountDao {

    public AccountDaoJPA() {
    }

    public Account findByUsername(String username) {
        return getEntityManager().createNamedQuery("account.findByUsername", Account.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public Account findByCredentials(String username, String encryptedPassword) {
        return getEntityManager().createNamedQuery("account.findByCredentials", Account.class)
                .setParameter("username", username)
                .setParameter("password", encryptedPassword)
                .getSingleResult();
    }
}
