package service;

import dao.HashtagDao;
import dao.JPA;
import domain.Hashtag;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Thom van de Pas on 27-2-2018
 */
@Stateless
public class HashtagService {

    /**
     * Injects the HashtagDao and @JPA Annotation for the container
     */
    @Inject
    @JPA
    private HashtagDao hashtagDao;

    /**
     * Empty constructor for JPA.
     */
    public HashtagService() {
    }

    /**
     * Persists a new Hashtag using the Dao.
     *
     * @param hashtag
     * @returns the created Hashtag.
     */
    public Hashtag create(Hashtag hashtag) {
        return this.hashtagDao.create(hashtag);
    }

    /**
     * Updates a Hashtag via the Dao.
     *
     * @param hashtag
     * @returns the freshly updated Hashtag.
     */
    public Hashtag update(Hashtag hashtag) {
        return this.hashtagDao.update(hashtag);
    }

    /**
     * Deletes a Hashtag using the HashtagDao.
     *
     * @param hashtag
     */
    public void delete(Hashtag hashtag) {
        this.hashtagDao.delete(hashtag);
    }

    /**
     * Deletes a Hashtag based on its id.
     *
     * @param id
     */
    public void delete(Long id) {
        this.hashtagDao.deleteById(id);
    }

    /**
     * Finds a Hashtag by its id.
     *
     * @param id
     * @returns the found Hashtag.
     */
    public Hashtag findById(Long id) {
        return this.hashtagDao.findById(id);
    }

    /**
     * Finds a Hashtag based on its bodyText.
     *
     * @param bodyText
     * @returns the found Hashtags.
     */
    public List<Hashtag> findByBodyText(String bodyText) {
        return this.hashtagDao.findByBodyText(bodyText);
    }

    /**
     * Returns all the Hashtags.
     *
     * @return
     */
    public List<Hashtag> findAll() {
        return this.hashtagDao.findAll();
    }

    public void setDao(HashtagDao hashtagDao) {
        this.hashtagDao = hashtagDao;
    }
}
