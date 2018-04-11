package web.bean;

import main.domain.Account;
import main.service.AccountService;
import web.core.RedirectHelper;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author Thom van de Pas on 19-3-2018
 */
@Named("loginBean")
@ViewScoped
public class LoginBean implements Serializable {

    @Inject
    private AccountService accountService;
    @Inject
    private SessionBean sessionBean;

    private String username;
    private String password;
    private Account possibleAccount;

    public void init() {
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.login(this.username, this.password);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        Account loggedInAccount = this.accountService.findByUsername(request.getRemoteUser());
        this.sessionBean.setLoggedInAccount(loggedInAccount);

        boolean isRegular = request.isUserInRole("RegularRole");
        boolean isAdmin = request.isUserInRole("AdminRole");

        if (isAdmin) {
            RedirectHelper.redirect("/pages/admin/dashboard.xhtml");
        } else {
            RedirectHelper.redirect("/pages/regular/dashboard.xhtml");
        }
        return "";
    }

    //<editor-fold desc="Getters/Setters">
    public AccountService getService() {
        return accountService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getPossibleAccount() {
        return possibleAccount;
    }

    public void setPossibleAccount(Account possibleAccount) {
        this.possibleAccount = possibleAccount;
    }

    //</editor-fold>
}
