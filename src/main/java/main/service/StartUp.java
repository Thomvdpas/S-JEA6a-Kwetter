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
        profileSjef.setDateOfBirth("08-02-1978");
        profileSjef.setLocation("Tilburg");
        profileSjef.setAvatarPath("https://www.magentocommerce.com/magento-connect/media/catalog/product/cache/9/image/468x300/9df78eab33525d08d6e5fb8d27136e95/t/i/tinypng-magento-image_1.jpg");
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
        profileHenk.setDateOfBirth("23-11-1992");
        profileHenk.setAvatarPath("http://i.dailymail.co.uk/i/pix/tm/2007/07/lionking1807_468x325._to_468x312jpeg");
        profileHenk.setLastName("van der Pol");
        profileHenk.setBiography("Voetballen.");
        profileHenk.setAccount(accountHenk);
        accountHenk.setProfile(profileHenk);
        accountService.create(accountHenk);

        Kweet kweet = new Kweet("JEA is cool!", profileThom);
        kweet.addMention(profileThom);
        Kweet kweet2 = new Kweet("DPI was super leuk vandaag!", profileThom);
        Kweet kweet3 = new Kweet("Gisteren lekker Angular geklopt.", profileThom);
        kweet3.addHashtag(new Hashtag("yeah"));
        Kweet kweet4 = new Kweet("Vanmorgen met mijn goede been uit bed gestapt", profileThom);
        Kweet kweet5 = new Kweet("Wat is er lekkerder dan wakker worden door het gefluit van de vogeltjes?", profileThom);
        kweet5.addHashtag(new Hashtag("loveLife"));
        Kweet kweet6 = new Kweet("Net een heerlijk kopje Segafredo op.", profileThom);
        kweet6.addHashtag(new Hashtag("FairChain"));
        kweet6.addHashtag(new Hashtag("HeerlijkEerlijk"));
        Kweet kweet7 = new Kweet("Nieuwe iPhone X gekocht!", profileThom);
        Kweet kweet8 = new Kweet("Net eerste geworden op Fortnite", profileThom);
        Kweet kweet9 = new Kweet("Vanochtend een heerlijk bakje yoghurt op!!", profileThom);
        Kweet kweet10 = new Kweet("Logitech is een goed muis-merk.", profileThom);
        kweet10.addMention(profileThom);
        Kweet kweet11 = new Kweet("Vanavond lekker naar de bioscoop", profileThom);
        Kweet kweet12 = new Kweet("Zojuist een artikel gelezen over microservices, super interessant!", profileThom);
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

        profileThom.setLocation("Cahors");
        profileThom.setBiography("Ik speel iedere dag Pokémon Go!");
        profileThom.setAvatarPath("https://www.salford.ac.uk/__data/assets/image/0008/890072/varieties/lightbox.jpg");
        profileThom.addFollowing(profileSjef);
        profileSjef.addFollower(profileThom);
        profileThom.setDateOfBirth("11-07-1996");
        profileThom.addFollowing(profileHenk);
        profileHenk.addFollower(profileThom);
        profileService.update(profileThom);

        Kweet kweet13 = new Kweet("Test 1, 2, 3", profileSjef);
        Kweet kweet14 = new Kweet("Test 4, 5, 6", profileSjef);
        profileSjef.addKweet(kweet13);
        profileSjef.addKweet(kweet14);

        profileSjef.addFollowing(profileThom);
        profileThom.addFollower(profileSjef);
        profileSjef.addFollowing(profileHenk);
        profileHenk.addFollower(profileSjef);
        profileService.update(profileSjef);


        Kweet kweet15 = new Kweet("Mijn eerste Kweetje!", profileHenk);
        kweet15.addMention(profileThom);
        Kweet kweet16 = new Kweet("Net een nieuwe cavia gekocht!", profileHenk);
        profileHenk.addKweet(kweet15);
        profileHenk.addKweet(kweet16);

        profileHenk.addFollowing(profileThom);
        profileThom.addFollower(profileHenk);
        profileService.update(profileHenk);

        UserGroup userGroup = new UserGroup("Regular");
        UserGroup adminGroup = new UserGroup("Admin");

        userGroup.addAccount(accountSjef);
        userGroup.addAccount(accountHenk);
        this.userGroupService.create(userGroup);

        adminGroup.addAccount(accountThom);
        this.userGroupService.create(adminGroup);
    }
}
