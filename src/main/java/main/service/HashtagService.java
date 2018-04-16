package main.service;

import com.mysql.jdbc.StringUtils;
import main.dao.HashtagDao;
import main.dao.JPA;
import main.domain.Hashtag;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

import static main.util.HelperFunctions.isNull;

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
        if (!isNull(hashtag)) {
            return this.hashtagDao.create(hashtag);
        }
        return null;
    }

    /**
     * Updates a Hashtag via the Dao.
     *
     * @param hashtag
     * @returns the freshly updated Hashtag.
     */
    public Hashtag update(Hashtag hashtag) {
        if (!isNull(hashtag)) {
            return this.hashtagDao.update(hashtag);
        }
        return null;
    }

    /**
     * Deletes a Hashtag using the HashtagDao.
     *
     * @param hashtag
     */
    public void delete(Hashtag hashtag) {
        if (!isNull(hashtag)) {
            this.hashtagDao.delete(hashtag);
        }
    }

    /**
     * Deletes a Hashtag based on its id.
     *
     * @param id
     */
    public void delete(Long id) {
        if (!isNull(id)) {
            this.hashtagDao.deleteById(id);
        }
    }

    /**
     * Finds a Hashtag by its id.
     *
     * @param id
     * @returns the found Hashtag.
     */
    public Hashtag findById(Long id) {
        if (!isNull(id)) {
            return this.hashtagDao.findById(id);
        }
        return null;
    }

    /**
     * Finds a Hashtag based on its bodyText.
     *
     * @param bodyText
     * @returns the found Hashtags.
     */
    public Hashtag findByBodyText(String bodyText) {
        if (!StringUtils.isNullOrEmpty(bodyText)) {
            return this.hashtagDao.findByBodyText(bodyText);
        }
        return null;
    }

    /**
     * Returns all the Hashtags.
     *
     * @return
     */
    public List<Hashtag> findAll() {
        return this.hashtagDao.findAll();
    }

    public List<JsonObject> allToJson(List<Hashtag> hashtags) {
        List<JsonObject> jsonObjects = new ArrayList<JsonObject>();
        for (Hashtag hashtag : hashtags) {
            jsonObjects.add(hashtag.toJson());
        }
        return jsonObjects;
    }

    public void setDao(HashtagDao hashtagDao) {
        this.hashtagDao = hashtagDao;
    }
}
