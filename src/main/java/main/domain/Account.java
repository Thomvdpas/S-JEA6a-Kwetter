package main.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.util.EncryptionHelper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author Thom van de Pas on 27-2-2018
 */
//@Getter
//@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "account.findByUsername", query = "SELECT a FROM Account a WHERE a.username = :username"),
})
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 4, max = 20)
    private String username;

    @Column(unique = true)
    @Email
    private String emailaddress;

    @JsonIgnore
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "accounts")
    private List<UserGroup> userGroups;


    public Account() {
        this.userGroups = new ArrayList<>();
    }

    public Account(String username, String emailaddress, String password) {
        this();
        this.username = username;
        this.emailaddress = emailaddress;
        try {
            this.password = EncryptionHelper.encryptData(password);
        } catch (Exception ex) {
            System.out.println("exception message = " + ex.getMessage());
        }
        this.profile = new Profile();
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("username", this.username)
                .add("emailaddress", this.emailaddress)
                .add("profile", Json.createObjectBuilder()
                        .add("firstName", profile.getFirstName())
                        .add("lastName", profile.getLastName()).build())
                .build();
    }

    //<editor-fold desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        try {
            this.password = EncryptionHelper.encryptData(password);
        } catch (Exception ex) {
            System.out.println("exception message = " + ex.getMessage());
        }
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Collection<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroup(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public void clearGroup() {
        this.userGroups.clear();
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
