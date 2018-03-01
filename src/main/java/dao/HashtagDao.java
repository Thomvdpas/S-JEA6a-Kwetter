package dao;

import domain.Hashtag;

import java.util.ArrayList;

/**
 * @author Thom van de Pas on 27-2-2018
 */
public interface HashtagDao {

    void addHashtag(Hashtag hashtag);

    void removeHashtag(Hashtag hashtag);

    Hashtag findByBodyText(String bodyText);

    Hashtag findById(Long id);

    ArrayList<Hashtag> getHashtags();
}
