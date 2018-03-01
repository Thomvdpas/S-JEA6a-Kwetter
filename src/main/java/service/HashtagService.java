package service;

import dao.JPA;
import dao.jpa.HashtagDaoJPA;
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
    private HashtagDaoJPA hashtagDao;

    public HashtagService() {
    }

    public Hashtag findByBodyText(String bodyText) {
        return hashtagDao.findByBodyText(bodyText);
    }

    public ArrayList<Hashtag> getHashtags() {
        return hashtagDao.getHashtags();
    }
}
