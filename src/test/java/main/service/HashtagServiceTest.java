package main.service;


import main.dao.HashtagDao;
import main.domain.Hashtag;
import main.domain.Kweet;
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
public class HashtagServiceTest {

    private HashtagService service;
    @Mock
    private HashtagDao hashtagDao;

    Hashtag hashtag = null;
    Kweet kweet = null;

    @Before
    public void setUp() {
        service = new HashtagService();
        service.setDao(hashtagDao);
        kweet = new Kweet();
        hashtag = new Hashtag("Test");
        hashtag.setId(1L);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void creatingSuccessful() {
        service.create(hashtag);
        verify(hashtagDao, Mockito.times(1)).create(hashtag);
    }

    @Test
    public void findHashtagSuccessful() {
        when(service.findById(hashtag.getId())).thenReturn(hashtag);
        Hashtag found = service.findById(hashtag.getId());
        assertThat(found, is(hashtag));
    }
}
