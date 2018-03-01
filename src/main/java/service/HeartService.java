package service;

import dao.HeartDao;
import dao.JPA;
import dao.jpa.HeartDaoJPA;
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
    private HeartDaoJPA heartDao;

    public Heart findByKweet(Kweet kweet) {
        return heartDao.findByKweet(kweet);
    }

    public ArrayList<Heart> getHearts() {
        return heartDao.getHearts();
    }
}
