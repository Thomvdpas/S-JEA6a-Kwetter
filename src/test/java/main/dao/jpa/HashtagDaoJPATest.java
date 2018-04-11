package main.dao.jpa;

import main.domain.Account;
import main.domain.Hashtag;
import main.domain.Kweet;
import main.domain.Profile;
import main.util.DatabaseCleaner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Thom van de Pas on 15-3-2018
 */
@Ignore
public class HashtagDaoJPATest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccountTestPU");
    private Account firstAccount;
    private EntityManager em;
    private EntityTransaction tx;
    private HashtagDaoJPA hashtagDao;
    private Profile profileFirst;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException e) {
            Logger.getLogger(HashtagDaoJPA.class.getName()).log(Level.SEVERE, null, e);
        }

        em = emf.createEntityManager();
        tx = em.getTransaction();

        hashtagDao = new HashtagDaoJPA();
        hashtagDao.setEntityManager(em);
        this.firstAccount = new Account();
    }

    @Test
    public void addHashtag() {
        Hashtag hashtag = new Hashtag("Soccer");

        tx.begin();
        hashtagDao.create(hashtag);
        List<Hashtag> foundHashtags = hashtagDao.findAll();
        tx.commit();

        Hashtag savedHashtag = foundHashtags.get(0);

        Assert.assertEquals("Soccer", savedHashtag.getBodyText());
        Assert.assertEquals(1, foundHashtags.size());
        Assert.assertNotEquals(2, foundHashtags.size());
    }

    @Test
    public void getKweetsHashtag() {
        Hashtag hashtag = new Hashtag("Soccer");

        tx.begin();
        hashtagDao.create(hashtag);
        List<Hashtag> foundHashtags = hashtagDao.findAll();
        tx.commit();

        profileFirst.setLocation("Tilburg");
        profileFirst.setBiography("I like football");


        Kweet kweet = new Kweet("Gisteren lekker gefietst.", firstAccount);
        hashtag.addKweet(kweet);

        tx.begin();
        hashtagDao.update(hashtag);
        tx.commit();

        Assert.assertEquals(1, hashtag.getKweets().size());
        Assert.assertEquals(1, foundHashtags.size());
        Assert.assertNotEquals(2, foundHashtags.size());
        Assert.assertNotEquals(2, hashtag.getKweets().size());
        Assert.assertEquals("Gisteren lekker gefietst.", hashtag.getKweets().get(0).getMessageBody());
    }
}
