package dao;

import domain.Account;

/**
 * @author Thom van de Pas on 1-3-2018
 */
public interface AccountDao extends GenericDao<Account> {

    Account findByUsername(String username);
}
