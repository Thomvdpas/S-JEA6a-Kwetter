package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * @author Thom van de Pas on 27-2-2018
 */
//@Getter
//@Setter
@Entity
public class UserRole implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private UserAccount userAccount;

    public UserRole() {
    }

    //<editor-fold desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    //</editor-fold>
}
