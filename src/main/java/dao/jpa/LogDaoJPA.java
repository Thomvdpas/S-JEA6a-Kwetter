package dao.jpa;

import dao.GenericDaoJPAImpl;
import dao.JPA;
import dao.LogDao;
import domain.Log;

import javax.ejb.Stateless;

/**
 * @author Thom van de Pas on 16-3-2018
 */
@Stateless
@JPA
public class LogDaoJPA extends GenericDaoJPAImpl<Log> implements LogDao {

    public LogDaoJPA() {
    }
}
