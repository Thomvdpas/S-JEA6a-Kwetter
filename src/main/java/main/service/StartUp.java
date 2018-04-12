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
    private GroupService groupService;
    @Inject
    private ProfileService profileService;


    public StartUp() {

    }

    /**
     * Creates some data to test the API with.
     */
    @PostConstruct
    public void initData() {
        Account account1 = new Account();
        account1.setUsername("thomvdpas");
        account1.setEmailaddress("thomvandepas@hotmail.com");
        account1.setPassword("Test!2");
        Profile profile1 = new Profile();
        profile1.setFirstName("Thom");
        profile1.setLastName("van de Pas");
        profile1.setAccount(account1);
        account1.setProfile(profile1);
        accountService.create(account1);

        Account account2 = new Account();
        account2.setUsername("sjef2");
        account2.setEmailaddress("sjefbeun@hotmail.com");
        account2.setPassword("sjefje!@#");
        Profile profile2 = new Profile();
        profile2.setFirstName("Sjef");
        profile2.setLastName("Beun");
        profile2.setAccount(account2);
        account2.setProfile(profile2);
        accountService.create(account2);

        Account account3 = new Account();
        account3.setUsername("Henk");
        account3.setEmailaddress("henkPol@hotmail.com");
        account3.setPassword("Henkie!!");
        Profile profile3 = new Profile();
        profile3.setFirstName("Henk");
        profile3.setLastName("van der Pol");
        profile3.setAccount(account3);
        account3.setProfile(profile3);
        accountService.create(account3);

        Kweet kweet = new Kweet("Dit is een test Kweet", profile1);
        profile1.addKweet(kweet);

        Heart heart = new Heart(kweet, profile1);
        kweet.addHeart(heart);

        Hashtag hashtag = new Hashtag("#Football");
        kweet.addHashtag(hashtag);


        profile1.setLocation("Cahors");
        profile1.setBiography("Ik speel iedere dag Pokémon Go!");
        profile1.setAvatarPath("www.google.com/Search?search=test");
        profile1.addFollowing(profile2);
        profile1.addFollowing(profile3);
        profileService.update(profile1);

        profile2.addFollowing(profile1);
        profile2.addFollowing(profile3);
        profileService.update(profile2);

        profile3.addFollowing(profile1);
        profileService.update(profile3);

        UserGroup userGroup = new UserGroup("Regular");
        UserGroup adminGroup = new UserGroup("Admin");

        userGroup.addAccount(account1);
        userGroup.addAccount(account2);
        this.groupService.create(userGroup);

        adminGroup.addAccount(account1);
        this.groupService.create(adminGroup);
    }
}
