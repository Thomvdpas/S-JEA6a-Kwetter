package service;

import dao.HashtagDao;
import dao.JPA;
import domain.Hashtag;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;

/**
 * @author Thom van de Pas on 27-2-2018
 */
@Stateless
public class HashtagService {

    @Inject
    @JPA
    private HashtagDao hashtagDao;

    public HashtagService() {
    }

    public void addHashtag(Hashtag hashtag) {
        hashtagDao.addHashtag(hashtag);
    }

    public void removeHashtag(Hashtag hashtag) {
        hashtagDao.removeHashtag(hashtag);
    }

    public void removeHashtag(String bodyText) {
        hashtagDao.removeHashtag(findByBodyText(bodyText));
    }

    public Hashtag findByBodyText(String bodyText) {
        return hashtagDao.findByBodyText(bodyText);
    }

    public ArrayList<Hashtag> getHashtags() {
        return hashtagDao.getHashtags();
    }
}
