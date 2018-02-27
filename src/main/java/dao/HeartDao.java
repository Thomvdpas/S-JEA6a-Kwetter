package dao;

import domain.Heart;
import domain.Kweet;

import java.util.ArrayList;

/**
 * @author Thom van de Pas on 27-2-2018
 */
public interface HeartDao {

    void addHeart(Heart heart);

    void removeHeart(Heart heart);

    Heart findByKweet(Kweet kweet);

    ArrayList<Heart> getHearts();
}
