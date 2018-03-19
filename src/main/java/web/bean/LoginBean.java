package web.bean;

import main.domain.Account;
import main.service.AccountService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Thom van de Pas on 19-3-2018
 */
@Named("loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    @Inject
    private AccountService accountService;

    private String username;
    private String password;
    private Account possibleAccount;

    public void init() {
    }

    public void login() {
        try {
            possibleAccount = accountService.login(username, password);

            if (possibleAccount != null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Kwetter/profile.xhtml");
            }

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Oeps..", "Er is iets mis gegaan bij het inloggen."));
        }
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
