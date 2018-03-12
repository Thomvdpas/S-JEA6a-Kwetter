package dao;

import domain.Profile;

/**
 * @author Thom van de Pas on 1-3-2018
 */
public interface ProfileDao extends GenericDao<Profile> {

    Profile findByUsername(String username);
}
