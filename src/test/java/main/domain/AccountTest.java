package main.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Thom van de Pas on 1-3-2018
 */
public class AccountTest {

    private Account account = null;
    private Account account2 = null;
    private Profile profile = null;

    @Before
    public void setUp() {
        account = new Account();
        account2 = new Account("testUserName", "test@live.com", "testPassword");
        profile = new Profile();
    }

    @Test
    public void testGetAndSetId() {
        Long id = 12345L;
        account.setId(id);
        Long expResult = id;
        Long result = account.getId();
        assertEquals("ExpectedResult and Result are not equal", expResult, result);
    }

    @Test
    public void testGetAndSetUserName() {
        String userName = "AccountTest";
        account.setUsername(userName);
        String expResult = userName;
        String result = account.getUsername();
        assertEquals("ExpectedResult and Result are not equal", expResult, result);
    }

    @Test
    public void testGetAndSetEmailAddress() {
        String emailAddress = "testEmailAddress@live.com";
        account.setEmailaddress(emailAddress);
        String expResult = emailAddress;
        String result = account.getEmailaddress();
        assertEquals("ExpectedResult and Result are not equal", expResult, result);
    }

    @Test
    public void testGetAndSetPassword() {
        String password = "testPassword";
        account.setPassword(password);
        String expResult = password;
        String result = account.getPassword();
        assertEquals("ExpectedResult and Result are not equal", expResult, result);
    }

    @Test
    public void testGetAndSetProfile() {
        account.setProfile(profile);
        Profile expResult = profile;
        Profile result = account.getProfile();
        assertEquals("ExpectedResult and Result are not equal", expResult, result);
    }

    @Test
    public void testEquals() {
        Account test1 = new Account("Test", "test@live.nl", "test");
        String userName = "testUserName";
        String emailAddress = "test@live.com";
        String password = "testPassword";
        account.setUsername(userName);
        account.setEmailaddress(emailAddress);
        account.setPassword(password);

        assertFalse(account.equals(test1));
        assertTrue(account.equals(account2));
        assertTrue(account.equals(account));
    }

    @Test
    public void testHashCode() {
        Long id = 123L;
        account.setId(id);
        account2.setId(id);
        assertEquals("HashCodes are unidentical", account.hashCode(), account2.hashCode());
    }
}
