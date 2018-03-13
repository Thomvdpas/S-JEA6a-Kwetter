package event;

import domain.Account;

/**
 * @author Thom van de Pas on 13-3-2018
 */
public class AccountEvent {

    private Account account;

    public AccountEvent(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
