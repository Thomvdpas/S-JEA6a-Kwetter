package dao;

import domain.Heart;
import domain.Kweet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Thom van de Pas on 27-2-2018
 */
public interface HeartDao extends GenericDao<Heart> {

    List<Heart> findByKweet(Kweet kweet);
}
