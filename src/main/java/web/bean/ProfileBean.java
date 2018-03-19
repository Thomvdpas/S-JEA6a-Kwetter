package web.bean;

import main.domain.Account;
import main.service.AccountService;
import main.service.ProfileService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Thom van de Pas on 19-3-2018
 */
@Named("profileBean")
@ViewScoped
public class ProfileBean implements Serializable {

    @Inject
    private ProfileService profileService;
    @Inject
    private AccountService accountService;

    private Long id;

    private Account account;

    public void init() {
        if (id != null) {
            this.account = accountService.findById(id);
        }
    }

    //<editor-fold desc="Getters/Setters">
    public Long getId() {
        return id;
    }
    //</editor-fold>
}
