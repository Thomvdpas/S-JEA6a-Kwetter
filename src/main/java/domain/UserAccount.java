package domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Thom van de Pas on 27-2-2018
 */
@Getter
@Setter
public class UserAccount implements Serializable {

    private Long id;

    private String username;
    private String emailaddress;
    private String password;
    private UserProfile userProfile;

    public UserAccount() {}

    public UserAccount(String username, String emailaddress, String password) {
        this.username = username;
        this.emailaddress = emailaddress;
        this.password = password;
    }

    //<editor-fold desc="equals/hashCode">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount userAccount = (UserAccount) o;
        return Objects.equals(id, userAccount.id) &&
                Objects.equals(username, userAccount.username) &&
                Objects.equals(emailaddress, userAccount.emailaddress);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }
    //</editor-fold>
}
