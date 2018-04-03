package web.bean;

import main.domain.Account;
import org.omnifaces.util.Faces;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
import java.io.Serializable;

import static org.omnifaces.util.Faces.invalidateSession;
import static org.omnifaces.util.Faces.redirect;

/**
 * @author Thom van de Pas on 23-3-2018
 */
@Named
@SessionScoped
public class SessionBean implements Serializable {


    private Account loggedInAccount;

    /**
     * Logs a user out. Also invalidates the session and redirects the person to the login page.
     * @throws ServletException
     */
    public void logout() throws ServletException {
        this.loggedInAccount = null;

        Faces.logout();
        invalidateSession();
        redirect("login.xhtml?faces-redirect=true");
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public void setLoggedInAccount(Account loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
    }
}
