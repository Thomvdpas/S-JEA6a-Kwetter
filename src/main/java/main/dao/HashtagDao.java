package main.dao;

import main.domain.Hashtag;

/**
 * @author Thom van de Pas on 27-2-2018
 */
public interface HashtagDao extends GenericDao<Hashtag> {

    Hashtag findByBodyText(String bodyText);
}
