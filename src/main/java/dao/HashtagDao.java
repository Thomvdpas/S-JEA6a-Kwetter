package dao;

import domain.Hashtag;

import java.util.ArrayList;

/**
 * @author Thom van de Pas on 27-2-2018
 */
public interface HashtagDao extends GenericDao<Hashtag> {

    Hashtag findByBodyText(String bodyText);

    ArrayList<Hashtag> getHashtags();
}
