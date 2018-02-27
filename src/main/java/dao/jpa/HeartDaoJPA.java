package dao.jpa;

import dao.HeartDao;
import dao.JPA;
import domain.Hashtag;
import domain.Heart;
import domain.Kweet;

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
public class HeartDaoJPA implements HeartDao {

    @PersistenceContext
    private EntityManager entityManager;

    public HeartDaoJPA() {
    }


    public void addHeart(Heart heart) {
        entityManager.persist(heart);
    }

    public void removeHeart(Heart heart) {
        entityManager.remove(entityManager.merge(heart));
    }

    public Heart findByKweet(Kweet kweet) {
        TypedQuery<Heart> query = entityManager.createNamedQuery("heart.findByKweet", Heart.class);
        query.setParameter("kweet", kweet);
        List<Heart> result = query.getResultList();
        return result.get(0);
    }

    public ArrayList<Heart> getHearts() {
        Query query = entityManager.createQuery("SELECT h FROM Heart h");
        return new ArrayList<Heart>(query.getResultList());
    }
}
