package main.dao.jpa;

import main.domain.Account;
import main.domain.Role;
import org.junit.Before;
import org.junit.Test;
import main.util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Thom van de Pas on 2-3-2018
 */
public class AccountDaoJPATest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccountTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private AccountDaoJPA accountDao;

    private Account account = null;

    public AccountDaoJPATest() {
    }

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDaoJPA.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        this.accountDao = new AccountDaoJPA();
        accountDao.setEntityManager(em);
        account = new Account("testName", "testEmail@live.com", "testPassword", Role.GENERAL);
    }

    @Test
    public void testCreate() {
        Integer expResult = 1;

        tx.begin();
        this.accountDao.create(account);
        tx.commit();
        tx.begin();
        int amount = accountDao.findAll().size();
        tx.commit();
        assertThat(amount, is(expResult));
    }
}
