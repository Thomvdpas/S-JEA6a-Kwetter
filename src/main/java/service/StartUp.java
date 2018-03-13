package service;

import domain.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Date;

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
        Profile profile = new Profile("Thom", "van de Pas");
        profileService.create(profile);
        Profile profile2 = new Profile("Sjef", "Beun");
        profileService.create(profile2);

        Account account = new Account();
        account.setUsername("thomvdpas");
        account.setEmailaddress("thomvandepas@hotmail.com");
        account.setPassword("Test!2");
        account.setRole(Role.MODERATOR);
        account.setProfile(profile);
        accountService.create(account);

        Account account2 = new Account();
        account2.setUsername("sjef2");
        account2.setEmailaddress("sjefbeun@hotmail.com");
        account2.setPassword("sjefje!@#");
        account2.setRole(Role.GENERAL);
        account2.setProfile(profile2);
        accountService.create(account2);

        account.setProfile(profile);
        account2.setProfile(profile2);
        accountService.update(account);
        accountService.update(account2);
        profile.setAccount(account);
        profile2.setAccount(account2);
        profileService.update(profile);
        profileService.update(profile2);

        Kweet kweet = new Kweet("Dit is een test Kweet", profile);
        Heart heart = new Heart(new Date(), kweet);
        Hashtag hashtag = new Hashtag("test", kweet);
        hashtagService.create(hashtag);
        heartService.create(heart);
        kweetService.create(kweet);
    }
}
