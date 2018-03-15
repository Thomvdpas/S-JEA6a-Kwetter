package service;

import com.mysql.jdbc.StringUtils;
import dao.JPA;
import dao.KweetDao;
import dao.ProfileDao;
import domain.Hashtag;
import domain.Heart;
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

    @Inject
    private ProfileDao profileDao;

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
     * Method used to post a new Kweet if the Kweet has 140 characters at max.
     *
     * @param kweet is the new Kweet.
     * @returns the new Kweet.
     */
    public Kweet postKweet(Kweet kweet) {
        String messageBodyText = kweet.getMessageBody();
        if (!StringUtils.isNullOrEmpty(messageBodyText) && messageBodyText.length() <= 140) {
            Profile profile = this.profileDao.findById(kweet.getSender().getId());

            if (profile != null) {
                Kweet newKweet = kweetDao.create(kweet);
                profile.addKweet(newKweet);
                this.profileDao.update(profile);
                return newKweet;
            }
        }
        return null;
    }

    /**
     * Edits the body of a Kweet.
     *
     * @param kweet the Kweet where the body is modified from.
     * @returns the updated Kweet.
     */
    public Kweet editKweet(Kweet kweet) {
        String messageBodyText = kweet.getMessageBody();
        if (StringUtils.isNullOrEmpty(messageBodyText) && messageBodyText.length() >= 140) {
            return null;
        }

        Kweet originalKweet = kweetDao.findById(kweet.getId());
        if (originalKweet == null) {
            return null;
        }

        originalKweet.setMessageBody(kweet.getMessageBody());

        return this.kweetDao.update(originalKweet);
    }

    /**
     * Adds a Heart to a Kweet.
     * @param kweet the Kweet on which the heart has to be added to.
     * @param heart the Heart which has to be added.
     * @returns the updated Kweet.
     */
    public Kweet addHeart(Kweet kweet, Heart heart) {
        Kweet originalKweet = this.kweetDao.findById(kweet.getId());
        if (originalKweet != null && heart != null) {
            originalKweet.addHeart(heart);
        } else {
            return null;
        }
        for (Heart foundHeart : originalKweet.getHearts()) {
            if (foundHeart.equals(heart)) {
                return null;
            }
        }
        return this.kweetDao.update(originalKweet);
    }

    /**
     * Removes a Heart from a Kweet.
     * @param kweet the Kweet from which a Heart has to be removed.
     * @param heart the Heart which has to be removed.
     * @return the updated Kweet.
     */
    public Kweet removeHeart(Kweet kweet, Heart heart) {
        Kweet originalKweet = kweetDao.findById(kweet.getId());
        if (originalKweet.getHearts().contains(heart)) {
            originalKweet.getHearts().remove(heart);
        }

        return this.kweetDao.update(originalKweet);
    }

    /**
     * Adds a Hashtag object to a Kweet.
     * @param kweet the Kweet on which the Hashtag has to be added.
     * @param hashtag the Hashtag that has to be added to a Kweet.
     * @returns the updated Kweet.
     */
    public Kweet addHashtag(Kweet kweet, Hashtag hashtag) {
        Kweet originalKweet = this.kweetDao.findById(kweet.getId());

        if (originalKweet != null && hashtag != null) {
            if (!originalKweet.getHashtags().contains(hashtag)) {
                originalKweet.addHashtag(hashtag);
            }
        }
        return this.kweetDao.update(originalKweet);
    }

    /**
     * Gets a List of Kweets by bodytext.
     * @param bodyText the bodyText on which is searched.
     * @returns a List of Kweets.
     */
    public List<Kweet> findByBodyText(String bodyText) {
        if (!StringUtils.isNullOrEmpty(bodyText) && bodyText.length() <= 140) {
            return this.kweetDao.findByBodyText(bodyText);
        }
        return null;
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
