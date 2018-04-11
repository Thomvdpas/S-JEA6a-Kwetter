package main.dao;

import main.domain.Account;
import main.domain.Kweet;
import main.domain.Profile;

import java.util.List;

/**
 * @author Thom van de Pas on 27-2-2018
 */
public interface KweetDao extends GenericDao<Kweet> {

    List<Kweet> findByBodyText(String bodyText);

    List<Kweet> findBySender(Account sender);

    List<Kweet> findByMention(Profile mention);
}
