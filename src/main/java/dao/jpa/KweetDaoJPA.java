package dao.jpa;

import dao.GenericDaoJPAImpl;
import dao.JPA;
import dao.KweetDao;
import domain.Kweet;
import domain.Profile;

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

    public List<Kweet> findByBodyText(String bodyText) {
        return getEntityManager().createNamedQuery("kweet.findByBodyText", Kweet.class)
                .setParameter("bodyText", bodyText)
                .getResultList();
    }

    public List<Kweet> findBySender(Profile sender) {
        return getEntityManager().createNamedQuery("kweet.findByAccount", Kweet.class)
                .setParameter("sender", sender)
                .getResultList();
    }

    public List<Kweet> findByMention(Profile mention) {
        return getEntityManager().createNamedQuery("kweet.findByMention", Kweet.class)
                .setParameter("mention", mention)
                .getResultList();
    }
}
