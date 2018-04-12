package web.bean;

import main.domain.Account;
import main.domain.Kweet;
import main.domain.UserGroup;
import main.service.AccountService;
import main.service.KweetService;
import main.service.UserGroupService;
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
public class UserDetailsBean implements Serializable {

    @Inject
    private AccountService accountService;
    @Inject
    private KweetService kweetService;
    @Inject
    private UserGroupService userGroupService;

    private Account sender;
    private List<Kweet> kweets;
    private List<Kweet> filteredKweets;
    private Long accountId;
    private List<UserGroup> userGroups;
    private String userGroup;

    @PostConstruct
    public void init() {
        this.sender = this.accountService.findById(this.accountId);
        this.kweets = this.kweetService.findBySender(this.sender);
        this.userGroups = userGroupService.findAll();
    }

    public void remove(Kweet kweet) {
        if (kweet != null) {
            this.kweetService.delete(kweet);
            RedirectHelper.redirect("/pages/admin/details.xhtml?accountId=" + this.accountId);
        }
        FrontendHelper.displayErrorSmallMessage("Er ging iets mis.", "Probeer het opnieuw.");
    }

    public void update() {
        for (UserGroup userGroup : userGroups) {
            if (userGroup.getAccounts().contains(this.sender)) {
                userGroup.getAccounts().remove(this.sender);
                this.userGroupService.update(userGroup);
            }
        }

        UserGroup foundUserGroup = this.userGroupService.findByGroupName(this.userGroup);
        if (!foundUserGroup.getAccounts().contains(this.sender)) {
            foundUserGroup.addAccount(this.sender);
        }
        this.userGroupService.update(foundUserGroup);
        this.accountService.update(this.sender);
        RedirectHelper.redirect("/pages/admin/accounts.xhtml");
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

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getUserGroup() {
        return userGroup;
    }
}
