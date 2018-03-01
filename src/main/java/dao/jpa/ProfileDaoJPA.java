package dao.jpa;

import dao.GenericDaoJPAImpl;
import dao.ProfileDao;
import domain.Profile;

/**
 * @author Thom van de Pas on 1-3-2018
 */
public class ProfileDaoJPA extends GenericDaoJPAImpl<Profile> implements ProfileDao {

    public ProfileDaoJPA() {
    }
}
