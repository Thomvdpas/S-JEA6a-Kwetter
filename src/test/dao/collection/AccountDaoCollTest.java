package dao.collection;

import domain.Account;
import domain.Role;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Thom van de Pas on 1-3-2018
 */
public class AccountDaoCollTest {

    private AccountDaoColl accountDao;
    private Account account = null;
    private Account account2 = null;

    public AccountDaoCollTest() {
    }

    @Before
    public void setUp() {
        accountDao = new AccountDaoColl();
        account = new Account("testnaam", "testEmailadres@hotmail.com", "wachtwoord", Role.GENERAL);
        account2 = new Account("TestName", "TestEmail@live.com", "Password", Role.ADMINISTRATOR);
    }

    @Test
    public void testCreate() {
        accountDao.create(account);

//        assertEquals(accountDao.);
    }
}
