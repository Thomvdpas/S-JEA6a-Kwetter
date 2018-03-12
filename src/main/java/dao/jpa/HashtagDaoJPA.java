package dao.jpa;

import dao.GenericDaoJPAImpl;
import dao.HashtagDao;
import dao.JPA;
import domain.Hashtag;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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
                .setParameter("bodyText", bodyText)
                .getResultList();
    }
}
