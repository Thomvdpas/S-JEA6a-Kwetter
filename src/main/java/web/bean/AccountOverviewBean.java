package web.bean;

import main.domain.Account;
import main.service.AccountService;
import org.primefaces.event.SelectEvent;
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
public class AccountOverviewBean implements Serializable {

    @Inject
    private AccountService accountService;

    private List<Account> accounts;
    private List<Account> filteredAccounts;
    private Account selectedAccount;

    @PostConstruct
    public void init() {
        this.accounts = accountService.findAll();
    }

    public void onRowSelect(SelectEvent event) {
        Account selectedAccount = (Account) event.getObject();
        RedirectHelper.redirect("/pages/admin/details.xhtml?accountId=" + selectedAccount.getId());
    }

    public void remove(Account account) {
        if (account != null) {
            this.accountService.delete(account);
            RedirectHelper.redirect("/pages/admin/accounts.xhtml");
        }
        FrontendHelper.displayErrorSmallMessage("Er ging iets mis.", "Probeer het opnieuw.");
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getFilteredAccounts() {
        return filteredAccounts;
    }

    public void setFilteredAccounts(List<Account> filteredAccounts) {
        this.filteredAccounts = filteredAccounts;
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }
}
