package service;

import dao.HashtagDao;
import dao.JPA;
import dao.jpa.HashtagDaoJPA;
import domain.Hashtag;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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

    public Hashtag create(Hashtag hashtag) {
        return this.hashtagDao.create(hashtag);
    }

    public Hashtag update(Hashtag hashtag) {
        return this.hashtagDao.update(hashtag);
    }

    public void delete(Hashtag hashtag) {
        this.hashtagDao.delete(hashtag);
    }

    public void delete(Long id) {
        this.hashtagDao.deleteById(id);
    }

    public Hashtag findById(Long id) {
        return this.hashtagDao.findById(id);
    }

    public Hashtag findByBodyText(String bodyText) {
        return this.hashtagDao.findByBodyText(bodyText);
    }

    public List<Hashtag> findAll() {
        return this.hashtagDao.findAll();
    }
}
