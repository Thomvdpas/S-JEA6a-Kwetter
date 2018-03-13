package dao;

import domain.Kweet;
import domain.Profile;

import java.util.List;

/**
 * @author Thom van de Pas on 27-2-2018
 */
public interface KweetDao extends GenericDao<Kweet> {

    List<Kweet> findBySender(Profile sender);
}
