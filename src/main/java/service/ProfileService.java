package service;

import dao.JPA;
import dao.ProfileDao;
import domain.Profile;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Thom van de Pas on 9-3-2018
 */
@Stateless
public class ProfileService {

    @Inject
    @JPA
    private ProfileDao profileDao;

    public ProfileService() {
    }

    public Profile create(Profile profile) {
        return this.profileDao.create(profile);
    }

    public Profile update(Profile profile) {
        return this.profileDao.update(profile);
    }

    public void delete(Profile profile) {
        this.profileDao.delete(profile);
    }

    public void delete(Long id) {
        this.profileDao.deleteById(id);
    }

    public Profile findById(Long id) {
        return this.profileDao.findById(id);
    }

    public List<Profile> findAll() {
        return this.profileDao.findAll();
    }
}
