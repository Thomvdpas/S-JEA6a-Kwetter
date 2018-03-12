package service;

import domain.Account;
import domain.Profile;
import domain.Role;

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
     * Injects the AccountService for putting some Accounts in the DataBase.
     */
    @Inject
    private AccountService accountService;

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
        account.setRole(Role.MODERATOR);
        account.setProfile(new Profile());
        accountService.create(account);

        Account account2 = new Account();
        account2.setUsername("sjef2");
        account2.setEmailaddress("sjefbeun@hotmail.com");
        account2.setPassword("sjefje!@#");
        account2.setRole(Role.GENERAL);
        account2.setProfile(new Profile());
        accountService.create(account2);
    }
}
