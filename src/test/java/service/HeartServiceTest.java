package service;

import dao.jpa.HeartDaoJPA;
import domain.Heart;
import domain.Kweet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Thom van de Pas on 12-3-2018
 */
@RunWith(MockitoJUnitRunner.class)
public class HeartServiceTest {

    private HeartService service;
    @Mock
    private HeartDaoJPA heartDao;

    @Before
    public void setUp() {
        service = new HeartService();
        service.setDao(heartDao);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void savingHeartSuccessful() {
        Heart heart = new Heart(new Date(), new Kweet());
        service.create(heart);
        verify(heartDao, Mockito.times(1)).create(heart);
    }

    @Test
    public void findHeartSuccessfull() {
        Heart heart = new Heart(new Date(), new Kweet());
        heart.setId(1L);

        when(service.findById(heart.getId())).thenReturn(heart);
        Heart found = service.findById(heart.getId());
        assertThat(found, is(heart));
    }
}
