package dao.collection;

import dao.HashtagDao;
import domain.Hashtag;

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
public class HashtagDaoColl implements HashtagDao {

    CopyOnWriteArrayList<Hashtag> hashtags = new CopyOnWriteArrayList<Hashtag>();


    @PostConstruct
    private void init() {
    }

    public HashtagDaoColl() {
    }

    public Hashtag findByBodyText(String bodyText) {
        for (Hashtag hashtag : hashtags) {
            if (hashtag.getBodyText().contentEquals(bodyText)) {
                return hashtag;
            }
        }
        return null;
    }

    public ArrayList<Hashtag> getHashtags() {
        return new ArrayList<Hashtag>();
    }

    public Hashtag create(Hashtag hashtag) {
        hashtags.add(hashtag);
        return hashtag;
    }

    public Hashtag update(Hashtag hashtag) {
        return null;
    }

    public void delete(Long id) {
        Hashtag hashtag = findById(id);
        if (hashtag != null) {
            hashtags.remove(hashtag);
        }
    }

    public Hashtag findById(Long id) {
        for (Hashtag hashtag : hashtags) {
            if (hashtag.getId().equals(id)) {
                return hashtag;
            }
        }
        return null;
    }
}
