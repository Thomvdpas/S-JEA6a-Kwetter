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
        profileSjef.setAccount(accountSjef);
        accountSjef.setProfile(profileSjef);
        accountService.create(accountSjef);

        Account accountHenk = new Account();
        accountHenk.setUsername("Henk");
        accountHenk.setEmailaddress("henkPol@hotmail.com");
        accountHenk.setPassword("Henkie!!");
        Profile profileHenk = new Profile();
        profileHenk.setFirstName("Henk");
        profileHenk.setLastName("van der Pol");
        profileHenk.setBiography("Voetballen.");
        profileHenk.setAccount(accountHenk);
        accountHenk.setProfile(profileHenk);
        accountService.create(accountHenk);

        Kweet kweet = new Kweet("Dit is een test Kweet", profileThom);
        Kweet kweet2 = new Kweet("Test Kweet 2", profileThom);
        Kweet kweet3 = new Kweet("Test Kweet 3", profileThom);
        profileThom.addKweet(kweet);
        profileThom.addKweet(kweet2);
        profileThom.addKweet(kweet3);

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

        Kweet kweet4 = new Kweet("Test Kweet Sjef 1", profileSjef);
        Kweet kweet5 = new Kweet("Test Kweet Sjef 2", profileSjef);
        profileSjef.addKweet(kweet4);
        profileSjef.addKweet(kweet5);

        profileSjef.addFollowing(profileThom);
        profileSjef.addFollowing(profileHenk);
        profileService.update(profileSjef);


        Kweet kweet6 = new Kweet("Test Kweet Henk 1", profileHenk);
        Kweet kweet7 = new Kweet("Test Kweet Henk 2", profileHenk);
        profileHenk.addKweet(kweet6);
        profileHenk.addKweet(kweet7);

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
