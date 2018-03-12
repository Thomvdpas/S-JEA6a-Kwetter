package dao.jpa;

import dao.GenericDaoJPAImpl;
import dao.JPA;
import dao.KweetDao;
import domain.Account;
import domain.Kweet;

import javax.ejb.Stateless;
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
        return getEntityManager().createNamedQuery("kweet.findByAccount", Kweet.class)
                .setParameter("sender", sender)
                .getResultList();
    }
}
