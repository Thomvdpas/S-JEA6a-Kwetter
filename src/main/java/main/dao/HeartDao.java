package main.dao;

import main.domain.Heart;
import main.domain.Kweet;

import java.util.List;

/**
 * @author Thom van de Pas on 27-2-2018
 */
public interface HeartDao extends GenericDao<Heart> {

    List<Heart> findByKweet(Kweet kweet);
}
