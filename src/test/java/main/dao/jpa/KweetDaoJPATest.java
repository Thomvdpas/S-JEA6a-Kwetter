package main.dao.jpa;

import main.domain.*;
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
public class KweetDaoJPATest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccountTestPU");
    private Account firstAccount;
    private EntityManager em;
    private EntityTransaction tx;
    private KweetDaoJPA kweetDao;
    private Profile profileFirst;

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException e) {
            Logger.getLogger(KweetDaoJPA.class.getName()).log(Level.SEVERE, null, e);
        }

        em = emf.createEntityManager();
        tx = em.getTransaction();

        kweetDao = new KweetDaoJPA();
        kweetDao.setEntityManager(em);
        this.firstAccount = new Account();
    }

    @Test
    public void addKweet() {
        profileFirst.setLocation("Tilburg");
        profileFirst.setBiography("I like football");

        Kweet kweet = new Kweet("Gisteren lekker gefietst.", firstAccount);

        tx.begin();
        kweetDao.create(kweet);
        List<Kweet> foundKweets = kweetDao.findAll();
        tx.commit();

        Kweet savedKweet = foundKweets.get(0);

        Assert.assertEquals("Gisteren lekker gefietst.", savedKweet.getMessageBody());
        Assert.assertEquals(1, foundKweets.size());
        Assert.assertNotEquals(2, foundKweets.size());

    }

    @Test
    public void addHashtagsToKweet() {
        profileFirst.setLocation("Tilburg");
        profileFirst.setBiography("I like football");

        Kweet kweet = new Kweet("Gisteren lekker gefietst.", firstAccount);

        kweet.addHashtag(new Hashtag("Fietsen"));

        tx.begin();
        kweetDao.create(kweet);
        List<Kweet> foundKweets = kweetDao.findAll();
        tx.commit();

        Kweet savedKweet = foundKweets.get(0);
        List<Hashtag> hashtags = savedKweet.getHashtags();

        Hashtag foundHashtags = hashtags.get(0);

        Assert.assertEquals(1, foundKweets.size());
        Assert.assertEquals(1, hashtags.size());
        Assert.assertEquals("Fietsen", foundHashtags.getBodyText());
        Assert.assertNotEquals(2, hashtags.size());
        Assert.assertNotEquals(2, foundKweets.size());

        tx.begin();
        kweet.getHashtags().remove(0);
        kweetDao.update(kweet);
        tx.commit();
    }

    @Test
    public void addMentionToKweet() {
        profileFirst.setLocation("Tilburg");
        profileFirst.setBiography("I like football");

        Profile profileSecond = new Profile();
        profileSecond.setLocation("Amsterdam");
        profileSecond.setBiography("I like tennis");

        Kweet kweet = new Kweet("Gisteren lekker gefietst", firstAccount);
        kweet.addMention(profileSecond);

        tx.begin();
        kweetDao.create(kweet);
        List<Kweet> kweets = kweetDao.findAll();
        tx.commit();

        Kweet savedKweet = kweets.get(0);
        List<Profile> mentions = savedKweet.getMentions();

        Profile mentionedProfile = mentions.get(0);

        Assert.assertEquals(profileSecond, mentionedProfile);
        Assert.assertEquals(profileSecond.getId(), mentionedProfile.getId());
        Assert.assertEquals(1, mentions.size());
        Assert.assertEquals(1, kweets.size());
        Assert.assertNotEquals(2, kweets.size());
        Assert.assertNotEquals(2, mentions.size());

        tx.begin();
        kweet.getMentions().remove(0);
        kweetDao.update(kweet);
        tx.commit();
    }

    @Test
    public void addKweetLikes() {
        profileFirst.setLocation("Tilburg");
        profileFirst.setBiography("I like football");

        Profile profileSecond = new Profile();
        profileSecond.setLocation("Amsterdam");
        profileSecond.setBiography("I like tennis");

        Kweet kweet = new Kweet("Gisteren lekker gefietst", firstAccount);
        tx.begin();
        kweetDao.create(kweet);
        List<Kweet> kweets = kweetDao.findAll();
        tx.commit();

        Kweet savedKweet = kweets.get(0);
        Heart heart = new Heart(savedKweet, profileSecond);
        savedKweet.addHeart(heart);

        tx.begin();
        kweetDao.update(savedKweet);
        Kweet foundKweet = kweetDao.findById(savedKweet.getId());
        tx.commit();

        Assert.assertEquals(1, foundKweet.getHearts().size());
        Assert.assertNotEquals(2, foundKweet.getHearts());
        Assert.assertEquals(profileSecond.getBiography(), foundKweet.getHearts().get(0).getSender().getBiography());
        Assert.assertEquals(foundKweet, foundKweet.getHearts().get(0).getKweet());

        tx.begin();
        savedKweet.getHearts().remove(0);
        kweetDao.update(kweet);
        tx.commit();
    }
}
