package service;

import dao.JPA;
import dao.ProfileDao;
import domain.Profile;
import interceptor.LoggingInterceptor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * @author Thom van de Pas on 9-3-2018
 */
@Stateless
public class ProfileService {

    /**
     * Injects the ProfileDao and uses @JPA annotation.
     */
    @Inject
    @JPA
    private ProfileDao profileDao;

    /**
     * Empty constructor for JPA purposes.
     */
    public ProfileService() {
    }

    /**
     * Finds a Profile by the username of the Account.
     *
     * @param username
     * @returns the found Profile or null.
     */
    public Profile findByUsername(String username) {
        return this.profileDao.findByUsername(username);
    }

    /**
     * Creates a new profile and stores it in the database via the dao.
     *
     * @param profile
     * @returns the created Profile
     */
    @Interceptors(LoggingInterceptor.class)
    public Profile create(Profile profile) {
        return this.profileDao.create(profile);
    }

    /**
     * Updates a Profile by storing it in the DataBase via the dao.
     *
     * @param profile
     * @returns the updated Profile.
     */
    public Profile update(Profile profile) {
        return this.profileDao.update(profile);
    }

    /**
     * Deletes a Profile.
     *
     * @param profile
     */
    public void delete(Profile profile) {
        this.profileDao.delete(profile);
    }

    /**
     * Deletes a Profile based on the id of a Profile.
     *
     * @param id
     */
    public void delete(Long id) {
        this.profileDao.deleteById(id);
    }

    /**
     * Finds a Profile using the id.
     *
     * @param id
     * @returns the found Profile and returns null if there is no Profile with that id.
     */
    public Profile findById(Long id) {
        return this.profileDao.findById(id);
    }

    /**
     * Finds all the Profiles
     *
     * @returns a List of Profiles.
     */
    public List<Profile> findAll() {
        return this.profileDao.findAll();
    }

    public void setDao(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }
}
