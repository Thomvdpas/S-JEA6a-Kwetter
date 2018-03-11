package service;

import dao.JPA;
import dao.KweetDao;
import domain.Account;
import domain.Kweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Thom van de Pas on 9-3-2018
 */
@Stateless
public class KweetService {

    @Inject
    @JPA
    private KweetDao kweetDao;

    public KweetService() {
    }

    public List<Kweet> findBySender(Account account) {
        return this.kweetDao.findBySender(account);
    }

    public Kweet create(Kweet kweet) {
        return this.kweetDao.create(kweet);
    }

    public Kweet update(Kweet kweet) {
        return this.kweetDao.update(kweet);
    }

    public void delete(Kweet kweet) {
        this.kweetDao.delete(kweet);
    }

    public void delete(Long id) {
        this.kweetDao.deleteById(id);
    }

    public Kweet findById(Long id) {
        return this.kweetDao.findById(id);
    }

    public List<Kweet> findAll() {
        return this.kweetDao.findAll();
    }
}
