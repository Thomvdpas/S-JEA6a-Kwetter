package dao.jpa;

import dao.GenericDaoJPAImpl;
import dao.JPA;
import dao.KweetDao;
import domain.Account;
import domain.Hashtag;
import domain.Kweet;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Thom van de Pas on 1-3-2018
 */
@Stateless
@JPA
public class KweetDaoJPA extends GenericDaoJPAImpl<Kweet> implements KweetDao {

    public KweetDaoJPA() {
    }

    public List<Kweet> findBySender(Account sender) {
        TypedQuery<Kweet> query = entityManager.createNamedQuery("kweet.findByAccount", Kweet.class);
        query.setParameter("sender", sender);
        List<Kweet> result = query.getResultList();
        return result;
    }
}
