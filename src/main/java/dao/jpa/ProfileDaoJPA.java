package dao.jpa;

import dao.GenericDaoJPAImpl;
import dao.JPA;
import dao.ProfileDao;
import domain.Account;
import domain.Profile;

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
