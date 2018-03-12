package service;

import dao.HeartDao;
import dao.JPA;
import domain.Heart;
import domain.Kweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Thom van de Pas on 27-2-2018
 */
@Stateless
public class HeartService {

    @Inject
    @JPA
    private HeartDao heartDao;

    public HeartService() {
    }

    /**
     * Persists a new Heart.
     *
     * @param heart
     * @returns the newly persisted Heart.
     */
    public Heart create(Heart heart) {
        return this.heartDao.create(heart);
    }

    /**
     * Updates a Heart
     *
     * @param heart
     * @returns the updated Heart
     */
    public Heart update(Heart heart) {
        return this.heartDao.update(heart);
    }

    /**
     * Deletes a Heart
     *
     * @param heart
     */
    public void delete(Heart heart) {
        this.heartDao.delete(heart);
    }

    /**
     * Deletes an Heart based on its id.
     *
     * @param id
     */
    public void delete(Long id) {
        this.heartDao.deleteById(id);
    }

    /**
     * Finds an Heart based on its Id.
     *
     * @param id
     * @return
     */
    public Heart findById(Long id) {
        return this.heartDao.findById(id);
    }

    /**
     * Finds all the Hearts known in the DataBase.
     *
     * @return
     */
    public List<Heart> findAll() {
        return this.heartDao.findAll();
    }

    /**
     * Finds all the Hearts by a specific Kweet.
     *
     * @param kweet
     * @returns a list of Hearts
     */
    public List<Heart> findByKweet(Kweet kweet) {
        return this.heartDao.findByKweet(kweet);
    }

}
