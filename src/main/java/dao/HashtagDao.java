package dao;

import domain.Hashtag;

import java.util.List;

/**
 * @author Thom van de Pas on 27-2-2018
 */
public interface HashtagDao extends GenericDao<Hashtag> {

    List<Hashtag> findByBodyText(String bodyText);
}
