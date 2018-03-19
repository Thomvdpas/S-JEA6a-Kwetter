package main.dao.jpa;

import main.dao.GenericDaoJPAImpl;
import main.dao.JPA;
import main.dao.ProfileDao;
import main.domain.Profile;

import javax.ejb.Stateless;

/**
 * @author Thom van de Pas on 1-3-2018
 */
@Stateless
@JPA
public class ProfileDaoJPA extends GenericDaoJPAImpl<Profile> implements ProfileDao {

    public ProfileDaoJPA() {
    }

    public Profile findByUsername(String username) {
        return getEntityManager().createNamedQuery("profile.findByUsername", Profile.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
