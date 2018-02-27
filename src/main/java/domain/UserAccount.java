package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Thom van de Pas on 27-2-2018
 */
@Getter
@Setter
@Entity
public class UserAccount implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    @Size(min = 4, max = 20)
    private String username;

    @NotNull
    @Column(unique = true)
    @Email
    private String emailaddress;

    @NotNull
    @Size(min = 1, max = 16)
    private String password;

    @Column(unique = true)
    @OneToOne
    private UserProfile userProfile;

    @OneToOne
    private UserRole userRole;

    public UserAccount() {
    }

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
