package domain;


import domain.Hashtag;
import domain.Kweet;
import org.junit.Before;
import org.junit.Test;

import javax.ejb.BeforeCompletion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Thom van de Pas on 1-3-2018
 */
public class HashtagTest {

    private Hashtag hashtag = null;
    private Hashtag hashtag2 = null;
    private Kweet kweet = null;

    @Before
    public void setUp() {
        kweet = new Kweet();
        hashtag = new Hashtag();
        hashtag = new Hashtag("test", new Kweet());
    }

    @Test
    public void testSetId(){
        Long id = 1234L;
        hashtag.setId(id);
        Long expResult = id;
        Long result = hashtag.getId();
        assertEquals("ExpectedResult and Result are not equal", expResult, result);
    }

    @Test
    public void testGetId() {
        Long id = 12345L;
        hashtag.setId(id);
        Long expResult = id;
        Long result = hashtag.getId();
        assertEquals("ExpectedResult and Result are not equal", expResult, result);

    }

    @Test
    public void testGetAndSetBodyText() {
        String bodyText = "Test hashtag body";
        hashtag.setBodyText(bodyText);
        String expResult = bodyText;
        String result = hashtag.getBodyText();
        assertEquals("ExpectedResult and Result are not equal", expResult, result);
    }

    @Test
    public void testGetAndSetKweet() {
        hashtag.setKweet(kweet);
        Kweet expResult = kweet;
        Kweet result = hashtag.getKweet();
        assertEquals("ExpectedResult and Result are not equal", expResult, result);
    }

    @Test
    public void testEquals() {
        Hashtag test1 = new Hashtag();
        Hashtag test2 = new Hashtag();
        Long id = 123L;
        Long id2 = 1234L;
        hashtag.setId(id);
        test1.setId(id);
        test2.setId(id2);

        assertFalse(hashtag.equals(test2));
        assertTrue(hashtag.equals(test1));
        assertTrue(hashtag.equals(hashtag));
    }

    @Test
    public void testHashCode() {
        Long id = 123L;
        Hashtag test1 = new Hashtag();
        hashtag.setId(id);
        test1.setId(id);
        assertEquals("HashCodes are unidentical", hashtag.hashCode(), test1.hashCode());
    }
}
