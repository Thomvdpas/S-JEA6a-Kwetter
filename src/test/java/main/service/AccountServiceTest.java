package main.service;

import main.dao.jpa.AccountDaoJPA;
import main.domain.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * @author Thom van de Pas on 12-3-2018
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private AccountService service;
    @Mock
    private AccountDaoJPA accountDao;

    Account account = null;

    @Before
    public void setUp() {
        service = new AccountService();
        service.setDao(accountDao);
        account = new Account("testUsername", "testPassword", "test@testMail.com");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void creatingSuccessful() {
        service.create(account);
        verify(accountDao, Mockito.times(1)).create(account);
    }

    @Test
    public void findAccountSuccessful() {
        when(service.findByUsername(account.getUsername())).thenReturn(account);
        Account found = service.findByUsername(account.getUsername());
        assertThat(found, is(account));
    }
}
