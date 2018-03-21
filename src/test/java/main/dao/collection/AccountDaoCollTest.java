package main.dao.collection;

import main.domain.Account;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        this.accountDao = new AccountDaoColl();
        this.account = new Account("testnaam", "testEmailadres@hotmail.com", "wachtwoord");
        this.account2 = new Account("TestName", "TestEmail@live.com", "Password");
    }

    @Test
    public void testCreate() {
        this.accountDao.create(account);
        assertEquals(1, accountDao.accounts.size());
    }
}
