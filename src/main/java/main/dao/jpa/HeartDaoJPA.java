package main.dao.jpa;

import main.dao.GenericDaoJPAImpl;
import main.dao.HeartDao;
import main.dao.JPA;
import main.domain.Heart;
import main.domain.Kweet;

import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Thom van de Pas on 27-2-2018
 */
@Stateless
@JPA
public class HeartDaoJPA extends GenericDaoJPAImpl<Heart> implements HeartDao {

    public HeartDaoJPA() {
    }

    public List<Heart> findByKweet(Kweet kweet) {
        return getEntityManager().createNamedQuery("heart.findByKweet", Heart.class)
                .setParameter("kweet", kweet)
                .getResultList();
    }
}
