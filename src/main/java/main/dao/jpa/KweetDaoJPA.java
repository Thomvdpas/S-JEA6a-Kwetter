package main.dao.jpa;

import main.dao.GenericDaoJPAImpl;
import main.dao.JPA;
import main.dao.KweetDao;
import main.domain.Kweet;
import main.domain.Profile;

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

    public List<Kweet> findByBodyText(String bodyText) {
        return getEntityManager().createNamedQuery("kweet.findByBodyText", Kweet.class)
                .setParameter("bodyText", "%" +  bodyText + "%")
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

    @Override
    public List<Kweet> findFollowerKweetsBySender(Profile profile) {
        return getEntityManager().createNamedQuery("kweet.findByFollowings", Kweet.class)
                .setParameter("followings", profile.getFollowings())
                .getResultList();
    }

    @Override
    public List<Kweet> findMyLastTenKweets(Profile profile) {
        return getEntityManager().createNamedQuery("kweet.findMyKweetsOrderedByDate", Kweet.class)
                .setParameter("sender", profile)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public Kweet findLast(Profile sender) {
        TypedQuery<Kweet> query = getEntityManager().createNamedQuery("kweet.findLast", Kweet.class)
                .setParameter("sender", sender);

        return oneResult(query);
    }
}
