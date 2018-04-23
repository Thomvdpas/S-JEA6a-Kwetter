package main.dao;

import main.domain.Kweet;
import main.domain.Profile;

import java.util.List;

/**
 * @author Thom van de Pas on 27-2-2018
 */
public interface KweetDao extends GenericDao<Kweet> {

    List<Kweet> findByBodyText(String bodyText);

    List<Kweet> findBySender(Profile sender);

    List<Kweet> findByMention(Profile mention);

    List<Kweet> getTimelineKweets(Profile profile);

    List<Kweet> findMyLastTenKweets(Profile profile);

    Kweet findLast(Profile profile);

    List<Kweet> findByHashtagBodyText(String bodyText);
}
