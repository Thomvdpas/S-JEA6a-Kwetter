package dao.jpa;

import dao.GenericDaoJPAImpl;
import dao.JPA;
import dao.KweetDao;
import domain.Kweet;

import javax.ejb.Stateless;

/**
 * @author Thom van de Pas on 1-3-2018
 */
@Stateless
@JPA
public class KweetDaoJPA extends GenericDaoJPAImpl<Kweet> implements KweetDao {

    public KweetDaoJPA() {
    }
}
