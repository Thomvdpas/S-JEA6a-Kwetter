package service;

import domain.*;

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
    private HashtagService hashtagService;
    @Inject
    private HeartService heartService;
    @Inject
    private KweetService kweetService;
    @Inject
    private ProfileService profileService;

    public StartUp() {

    }

    /**
     * Creates some data to test the API with.
     */
    @PostConstruct
    public void initData() {
        Account account = new Account();
        account.setUsername("thomvdpas");
        account.setEmailaddress("thomvandepas@hotmail.com");
        account.setPassword("Test!2");
        Profile profile = new Profile();
        profile.setFirstName("Thom");
        profile.setLastName("van de Pas");
        account.setRole(Role.MODERATOR);
        account.setProfile(profile);
        accountService.create(account);

        Account account2 = new Account();
        account2.setUsername("sjef2");
        account2.setEmailaddress("sjefbeun@hotmail.com");
        account2.setPassword("sjefje!@#");
        Profile profile2 = new Profile();
        profile2.setFirstName("Sjef");
        profile2.setLastName("Beun");
        account2.setRole(Role.GENERAL);
        account2.setProfile(profile2);
        accountService.create(account2);

        Profile firstProfile = new Profile();
        firstProfile.setAccount(account2);
        firstProfile.setFirstName("Martijn");
        firstProfile.setLastName("Peijnenburg");
        firstProfile.setBiography("test");
        firstProfile.setAvatarPath("google.com/test");


        Kweet kweet = new Kweet("Dit is een test Kweet", firstProfile);
        firstProfile.addKweet(kweet);

        Heart heart = new Heart(kweet, firstProfile);
        kweet.addHeart(heart);

        Hashtag hashtag = new Hashtag("#Football");
        kweet.addHashtag(hashtag);

        profileService.create(firstProfile);

        firstProfile.setLocation("Tilburg");
        profileService.update(firstProfile);

        profile.setAccount(account);
        profile.setLocation("Cahors");
        profile.setBiography("Ik speel iedere dag Pokémon Go!");
        profile.setAvatarPath("www.google.com/Search?search=test");
        profileService.update(profile);
    }
}
