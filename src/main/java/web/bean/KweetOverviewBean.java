package web.bean;

import main.domain.Account;
import main.domain.Kweet;
import main.service.AccountService;
import main.service.KweetService;
import web.core.FrontendHelper;
import web.core.RedirectHelper;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Thom van de Pas on 10-4-2018
 */
@Named
@ViewScoped
public class KweetOverviewBean implements Serializable {

    @Inject
    private AccountService accountService;
    @Inject
    private KweetService kweetService;

    private Account sender;
    private List<Kweet> kweets;
    private List<Kweet> filteredKweets;
    private Long accountId;

    @PostConstruct
    public void init() {
        Account foundSender = this.accountService.findById(this.accountId);
        this.kweets = this.kweetService.findBySender(foundSender);
    }

    public void remove(Kweet kweet) {
        if (kweet != null) {
            this.kweetService.delete(kweet);
            RedirectHelper.redirect("/pages/admin/kweets.xhtml?accountId=" + this.accountId);
        }
        FrontendHelper.displayErrorSmallMessage("Er ging iets mis.", "Probeer het opnieuw.");
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public List<Kweet> getFilteredKweets() {
        return filteredKweets;
    }

    public void setFilteredKweets(List<Kweet> filteredKweets) {
        this.filteredKweets = filteredKweets;
    }
}
