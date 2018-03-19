package main.dao.jpa;

import main.dao.GenericDaoJPAImpl;
import main.dao.HashtagDao;
import main.dao.JPA;
import main.domain.Hashtag;

import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Thom van de Pas on 27-2-2018
 */
@Stateless
@JPA
public class HashtagDaoJPA extends GenericDaoJPAImpl<Hashtag> implements HashtagDao {

    public HashtagDaoJPA() {
    }

    public List<Hashtag> findByBodyText(String bodyText) {
        return getEntityManager().createNamedQuery("hashtag.findByBodyText", Hashtag.class)
                .setParameter("bodyText", "#" +  bodyText +"%")
                .getResultList();
    }
}
