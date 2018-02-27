package service;

import dao.HeartDao;
import dao.JPA;
import domain.Heart;
import domain.Kweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;

/**
 * @author Thom van de Pas on 27-2-2018
 */
@Stateless
public class HeartService {

    @Inject
    @JPA
    private HeartDao heartDao;

    public void addHeart(Heart heart) {
        heartDao.addHeart(heart);
    }

    public void removeHeart(Heart heart) {
        heartDao.removeHeart(heart);
    }

    public void removeHeart(Kweet kweet) {
        heartDao.removeHeart(findByKweet(kweet));
    }

    public Heart findByKweet(Kweet kweet) {
        return heartDao.findByKweet(kweet);
    }

    public ArrayList<Heart> getHearts() {
        return heartDao.getHearts();
    }
}
