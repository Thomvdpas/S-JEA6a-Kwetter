package main.dao.jpa;

import main.dao.GenericDaoJPAImpl;
import main.dao.JPA;
import main.dao.LogDao;
import main.domain.Log;

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
