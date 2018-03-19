package main.service;

import main.dao.JPA;
import main.dao.LogDao;
import main.domain.Log;

import javax.ejb.Stateless;
import javax.inject.Inject;

import static main.util.HelperFunctions.isNull;

/**
 * @author Thom van de Pas on 16-3-2018
 */
@Stateless
public class LogService {

    @Inject
    @JPA
    private LogDao logDao;

    public LogService() {
    }

    /**
     * Method to save a new Log.
     *
     * @param log is the new Log.
     * @returns the saved Log.
     */
    public Log save(Log log) {
        if (!isNull(log)) {
            return this.logDao.create(log);
        }
        return null;
    }
}
