package dao.jpa;

import dao.GenericDaoImpl;
import dao.HashtagDao;
import dao.JPA;
import domain.Hashtag;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thom van de Pas on 27-2-2018
 */
@Stateless
@JPA
public class HashtagDaoJPA extends GenericDaoImpl<Hashtag> implements HashtagDao {

    public HashtagDaoJPA() {
    }

    public Hashtag findByBodyText(String bodyText) {
        TypedQuery<Hashtag> query = entityManager.createNamedQuery("hashtag.findByBodyText", Hashtag.class);
        query.setParameter("bodyText", bodyText);
        List<Hashtag> result = query.getResultList();
        return result.get(0);
    }

    public ArrayList<Hashtag> getHashtags() {
        Query query = entityManager.createQuery("SELECT h FROM Hashtag h");
        return new ArrayList<Hashtag>(query.getResultList());
    }
}
