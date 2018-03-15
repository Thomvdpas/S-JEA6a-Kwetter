package dao.collection;

import dao.KweetDao;
import domain.Kweet;
import domain.Profile;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Thom van de Pas on 1-3-2018
 */
@Stateless
@Default
public class KweetDaoColl implements KweetDao {

    CopyOnWriteArrayList<Kweet> kweets = new CopyOnWriteArrayList<Kweet>();


    public Kweet create(Kweet kweet) {
        kweets.add(kweet);
        return kweet;
    }

    public Kweet update(Kweet kweet) {
        return null;
    }

    public void delete(Kweet kweet) {
        for (Kweet foundKweet : kweets) {
            if (foundKweet.equals(kweet)) {
                kweets.remove(foundKweet);
            }
        }
    }

    public void deleteById(Long id) {
        Kweet kweet = findById(id);
        if (kweet != null) {
            kweets.remove(kweet);
        }
    }

    public Kweet findById(Long id) {
        for (Kweet kweet : kweets) {
            if (kweet.getId().equals(id)) {
                return kweet;
            }
        }
        return null;
    }

    public List<Kweet> findAll() {
        return kweets;
    }

    public List<Kweet> findByBodyText(String bodyText) {
        return null;
    }

    public List<Kweet> findBySender(Profile sender) {
        return null;
    }

    public List<Kweet> findByMention(Profile mention) {
        return null;
    }
}
