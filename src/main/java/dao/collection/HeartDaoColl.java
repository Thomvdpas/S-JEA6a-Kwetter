package dao.collection;

import dao.HeartDao;
import domain.Hashtag;
import domain.Heart;
import domain.Kweet;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Thom van de Pas on 27-2-2018
 */
@Stateless
@Default
public class HeartDaoColl implements HeartDao {

    CopyOnWriteArrayList<Heart> hearts = new CopyOnWriteArrayList<Heart>();

    @PostConstruct
    private void init() {
    }

    public HeartDaoColl() {
    }

    public void addHeart(Heart heart) {
        hearts.add(heart);
    }

    public void removeHeart(Heart heart) {
        hearts.remove(heart);
    }

    public Heart findByKweet(Kweet kweet) {
        for (Heart heart : hearts) {
            if (heart.getKweet().equals(kweet)) {
                return heart;
            }
        }
        return null;
    }

    public Heart findById(Long id) {
        for (Heart heart : hearts) {
            if (heart.getId().equals(id)) {
                return heart;
            }
        }
        return null;
    }

    public ArrayList<Heart> getHearts() {
        return new ArrayList<Heart>();
    }
}
