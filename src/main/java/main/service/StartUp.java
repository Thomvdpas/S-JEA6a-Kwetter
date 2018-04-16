package main.service;

import main.domain.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * @author Thom van de Pas on 27-2-2018
 */

@Singleton
@Startup
public class StartUp {

    /**
     * Injects the Services for putting some entities in the DataBase.
     */
    @Inject
    private AccountService accountService;
    @Inject
    private UserGroupService userGroupService;
    @Inject
    private ProfileService profileService;


    public StartUp() {

    }

    /**
     * Creates some data to test the API with.
     */
    @PostConstruct
    public void initData() {
        Account accountThom = new Account();
        accountThom.setUsername("thomvdpas");
        accountThom.setEmailaddress("thomvandepas@hotmail.com");
        accountThom.setPassword("Test!2");
        Profile profileThom = new Profile();
        profileThom.setFirstName("Thom");
        profileThom.setLastName("van de Pas");
        profileThom.setAccount(accountThom);
        accountThom.setProfile(profileThom);
        accountService.create(accountThom);

        Account accountSjef = new Account();
        accountSjef.setUsername("sjef2");
        accountSjef.setEmailaddress("sjefbeun@hotmail.com");
        accountSjef.setPassword("sjefje!@#");
        Profile profileSjef = new Profile();
        profileSjef.setFirstName("Sjef");
        profileSjef.setLastName("Beun");
        profileSjef.setBiography("Hockeyen!");
        profileSjef.setLocation("Tilburg");
        profileSjef.setAvatarPath("https://www.twitter.com/CambridgeAnalytica");
        profileSjef.setAccount(accountSjef);
        accountSjef.setProfile(profileSjef);
        accountService.create(accountSjef);

        Account accountHenk = new Account();
        accountHenk.setUsername("Henk");
        accountHenk.setEmailaddress("henkPol@hotmail.com");
        accountHenk.setPassword("Henkie!!");
        Profile profileHenk = new Profile();
        profileHenk.setFirstName("Henk");
        profileHenk.setLocation("Parijs");
        profileHenk.setAvatarPath("https://www.facebook.com/CambridgeAnalytica");
        profileHenk.setLastName("van der Pol");
        profileHenk.setBiography("Voetballen.");
        profileHenk.setAccount(accountHenk);
        accountHenk.setProfile(profileHenk);
        accountService.create(accountHenk);

        Kweet kweet = new Kweet("Dit is een test Kweet", profileThom);
        kweet.addMention(profileThom);
        Kweet kweet2 = new Kweet("Test Kweet 2", profileThom);
        Kweet kweet3 = new Kweet("Test Kweet 3", profileThom);
        Kweet kweet4 = new Kweet("Dit is een test 4", profileThom);
        Kweet kweet5 = new Kweet("Test Kweet 5", profileThom);
        Kweet kweet6 = new Kweet("Test Kweet 6", profileThom);
        Kweet kweet7 = new Kweet("Dit is een 7", profileThom);
        Kweet kweet8 = new Kweet("Test Kweet 8", profileThom);
        Kweet kweet9 = new Kweet("Test Kweet 9", profileThom);
        Kweet kweet10 = new Kweet("Dit is een 10", profileThom);
        kweet10.addMention(profileThom);
        Kweet kweet11 = new Kweet("Test Kweet 11", profileThom);
        Kweet kweet12 = new Kweet("Test Kweet 12", profileThom);
        profileThom.addKweet(kweet);
        profileThom.addKweet(kweet2);
        profileThom.addKweet(kweet3);
        profileThom.addKweet(kweet4);
        profileThom.addKweet(kweet5);
        profileThom.addKweet(kweet6);
        profileThom.addKweet(kweet7);
        profileThom.addKweet(kweet8);
        profileThom.addKweet(kweet9);
        profileThom.addKweet(kweet10);
        profileThom.addKweet(kweet11);
        profileThom.addKweet(kweet12);

        Heart heart = new Heart(kweet, profileThom);
        kweet.addHeart(heart);

        Hashtag hashtag = new Hashtag("#Football");
        kweet.addHashtag(hashtag);

        profileThom.setLocation("Cahors");
        profileThom.setBiography("Ik speel iedere dag Pokémon Go!");
        profileThom.setAvatarPath("www.google.com/Search?search=test");
        profileThom.addFollowing(profileSjef);
        profileThom.addFollowing(profileHenk);
        profileService.update(profileThom);

        Kweet kweet13 = new Kweet("Test Kweet Sjef 1", profileSjef);
        Kweet kweet14 = new Kweet("Test Kweet Sjef 2", profileSjef);
        profileSjef.addKweet(kweet13);
        profileSjef.addKweet(kweet14);

        profileSjef.addFollowing(profileThom);
        profileSjef.addFollowing(profileHenk);
        profileService.update(profileSjef);


        Kweet kweet15 = new Kweet("Test Kweet Henk 1", profileHenk);
        kweet15.addMention(profileThom);
        Kweet kweet16 = new Kweet("Test Kweet Henk 2", profileHenk);
        profileHenk.addKweet(kweet15);
        profileHenk.addKweet(kweet16);

        profileHenk.addFollowing(profileThom);
        profileService.update(profileHenk);

        UserGroup userGroup = new UserGroup("Regular");
        UserGroup adminGroup = new UserGroup("Admin");

        userGroup.addAccount(accountSjef);
        this.userGroupService.create(userGroup);

        adminGroup.addAccount(accountThom);
        this.userGroupService.create(adminGroup);
    }
}
