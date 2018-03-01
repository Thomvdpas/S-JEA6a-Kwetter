package dao.jpa;

import dao.AccountDao;
import dao.GenericDaoJPAImpl;
import dao.JPA;
import domain.Account;

import javax.ejb.Stateless;

/**
 * @author Thom van de Pas on 1-3-2018
 */
@Stateless
@JPA
public class AccountDaoJPA extends GenericDaoJPAImpl<Account> implements AccountDao {

    public AccountDaoJPA() {
    }
}
