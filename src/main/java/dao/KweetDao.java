package dao;

import domain.Account;
import domain.Kweet;

import java.util.List;

/**
 * @author Thom van de Pas on 27-2-2018
 */
public interface KweetDao extends GenericDao<Kweet> {

    List<Kweet> findBySender(Account sender);
}
