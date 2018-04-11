package web.bean;

/**
 * @author Thom van de Pas on 11-4-2018
 */

import com.mysql.jdbc.StringUtils;
import main.domain.Account;
import main.domain.UserGroup;
import main.service.AccountService;
import main.service.GroupService;
import web.core.FrontendHelper;
import web.core.RedirectHelper;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class RegistrationBean implements Serializable {

    @Inject
    private AccountService accountService;
    @Inject
    private GroupService groupService;

    private UserGroup regularGroup;
    private String emailAddress;
    private String username;
    private String password;

    @PostConstruct
    public void init() {
        this.regularGroup = this.groupService.findByGroupName("Regular");
    }

    public void register() {
        if (!StringUtils.isNullOrEmpty(emailAddress) && !StringUtils.isNullOrEmpty(username) && !StringUtils.isNullOrEmpty(password)) {
            Account newAccount = new Account(username, emailAddress, password);
            this.accountService.create(newAccount);
            this.regularGroup.addAccount(newAccount);
            this.groupService.update(regularGroup);
            RedirectHelper.redirect("/login.xhtml");
        }
        FrontendHelper.displayErrorSmallMessage("Helaas.", "De velden zijn onjuist ingevuld.");
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
    //</editor-fold>
}
