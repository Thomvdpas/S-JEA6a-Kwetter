package service;

import dao.JPA;
import dao.KweetDao;
import domain.Kweet;
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
public class KweetService {

    /**
     * Injects the Service and has the JPA annotation
     * So that the container knows the JPA implementation should be used.
     */
    @Inject
    @JPA
    private KweetDao kweetDao;

    /**
     * Empty constructor for JPA purposes.
     */
    public KweetService() {
    }

    /**
     * A method that searches all the Kweets of a sender.
     *
     * @param account
     * @returns a List of Kweets or null if the Account didn't send any Kweet(s).
     */
    public List<Kweet> findBySender(Profile profile) {
        return this.kweetDao.findBySender(profile);
    }

    /**
     * Stores a new Kweet into the DataBase via the dao.
     *
     * @param kweet
     * @returns the newly persisted Kweet.
     */
    @Interceptors(LoggingInterceptor.class)
    public Kweet create(Kweet kweet) {
        return this.kweetDao.create(kweet);
    }

    /**
     * Updates a Kweet (If it is edited)
     *
     * @param kweet
     * @returns the updated Kweet.
     */
    public Kweet update(Kweet kweet) {
        return this.kweetDao.update(kweet);
    }

    /**
     * Removes a Kweet from the DataBase.
     *
     * @param kweet
     */
    public void delete(Kweet kweet) {
        this.kweetDao.delete(kweet);
    }

    /**
     * Removes a Kweet using it's id.
     *
     * @param id
     */
    public void delete(Long id) {
        this.kweetDao.deleteById(id);
    }

    /**
     * Finds a Kweet by its id.
     *
     * @param id
     * @returns the found Kweet or null if there is no Kweet known with that id.
     */
    public Kweet findById(Long id) {
        return this.kweetDao.findById(id);
    }

    /**
     * Finds all the Kweets.
     *
     * @returns all the Kweets known in the DataBase.
     */
    public List<Kweet> findAll() {
        return this.kweetDao.findAll();
    }

    public void setDao(KweetDao kweetDao) {
        this.kweetDao = kweetDao;
    }
}
