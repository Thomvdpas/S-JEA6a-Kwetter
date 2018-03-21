package main.service;

import main.dao.jpa.ProfileDaoJPA;
import main.domain.Account;
import main.domain.Profile;
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
public class ProfileServiceTest {

    private ProfileService service;
    @Mock
    private ProfileDaoJPA profileDao;

    @Before
    public void setUp() {
        service = new ProfileService();
        service.setDao(profileDao);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void savingHeartSuccessful() {
        Profile profile = new Profile();
        service.create(profile);
        verify(profileDao, Mockito.times(1)).create(profile);
    }

    @Test
    public void findProfileSuccessfull() {
        Account account = new Account("test", "test@live.nl", "testPw");
        Profile profile = new Profile();
        profile.setAccount(account);
        when(service.findByUsername(profile.getAccount().getUsername())).thenReturn(profile);
        Profile found = service.findByUsername(profile.getAccount().getUsername());
        assertThat(found, is(profile));
    }
}
