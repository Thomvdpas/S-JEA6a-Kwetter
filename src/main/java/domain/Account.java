package domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Thom van de Pas on 27-2-2018
 */
//@Getter
//@Setter
@Entity
public class Account implements Serializable {

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
    @Size(min = 4, max = 16)
    private String password;

    @Column(unique = true)
    @OneToOne
    private Profile profile;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Account() {
    }

    public Account(String username, String emailaddress, String password, Role role) {
        this.username = username;
        this.emailaddress = emailaddress;
        this.password = password;
        this.role = role;
    }

    //<editor-fold desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    //</editor-fold>

    //<editor-fold desc="equals/hashCode">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(username, account.username) &&
                Objects.equals(emailaddress, account.emailaddress);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }
    //</editor-fold>
}