package service;

import dao.HeartDao;
import dao.JPA;
import dao.jpa.HeartDaoJPA;
import domain.Hashtag;
import domain.Heart;
import domain.Kweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
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

    public Heart create(Heart heart) {
        return this.heartDao.create(heart);
    }

    public Heart update(Heart heart) {
        return this.heartDao.update(heart);
    }

    public void delete(Heart heart) {
        this.heartDao.delete(heart);
    }

    public void delete(Long id) {
        this.heartDao.deleteById(id);
    }

    public Heart findById(Long id) {
        return this.heartDao.findById(id);
    }

    public List<Heart> findAll() {
        return this.heartDao.findAll();
    }

    public Heart findByKweet(Kweet kweet) {
        return this.heartDao.findByKweet(kweet);
    }

}
