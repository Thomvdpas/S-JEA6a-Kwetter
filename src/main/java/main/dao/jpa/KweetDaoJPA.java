package main.dao.jpa;

import main.dao.GenericDaoJPAImpl;
import main.dao.JPA;
import main.dao.KweetDao;
import main.domain.Kweet;
import main.domain.Profile;

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
    public List<Kweet> getTimelineKweets(Profile profile) {
        return getEntityManager().createNamedQuery("kweet.getTimeline", Kweet.class)
                .setParameter("sender", profile)
                .setParameter("following", profile.getFollowings())
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
        return null;
    }

    @Override
    public List<Kweet> findByHashtagBodyText(String bodyText) {
        return getEntityManager().createNamedQuery("kweet.findByHashtagBodyText", Kweet.class)
                .setParameter("bodyText", bodyText)
                .getResultList();
    }
}
