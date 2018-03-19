package main.service;

import main.dao.jpa.KweetDaoJPA;
import main.domain.Kweet;
import main.domain.Profile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Thom van de Pas on 12-3-2018
 */
@RunWith(MockitoJUnitRunner.class)
public class KweetServiceTest {

    private KweetService service;
    @Mock
    private KweetDaoJPA kweetDao;

    Kweet kweet = null;

    @Before
    public void setUp() {
        service = new KweetService();
        service.setDao(kweetDao);
        kweet = new Kweet();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void savingHeartSuccessful() {
        Kweet kweet = new Kweet();
        service.create(kweet);
        verify(kweetDao, Mockito.times(1)).create(kweet);
    }

    @Test
    public void findKweetSuccessfull() {
        Kweet kweet = new Kweet();
        kweet.setSender(new Profile());
        kweet.setId(1L);
        when(service.findById(kweet.getId())).thenReturn(kweet);
        Kweet found = service.findById(kweet.getId());
        assertThat(found, is(kweet));
    }

    @Test
    public void kweetIsNull() {
        when(service.findById(1L)).thenReturn(kweet);
        Kweet foundKweet = service.findById(null);
        assertNull(foundKweet);
    }
}
