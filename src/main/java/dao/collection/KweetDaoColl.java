package dao.collection;

import dao.KweetDao;
import domain.Hashtag;
import domain.Kweet;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Thom van de Pas on 1-3-2018
 */
@Stateless
@Default
public class KweetDaoColl implements KweetDao{

    CopyOnWriteArrayList<Kweet> kweets = new CopyOnWriteArrayList<Kweet>();


    public Kweet create(Kweet kweet) {
        kweets.add(kweet);
        return kweet;
    }

    public Kweet update(Kweet kweet) {
        return null;
    }

    public void delete(Long id) {
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
}
