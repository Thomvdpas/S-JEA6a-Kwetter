package domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Thom van de Pas on 27-2-2018
 */
//@Getter
//@Setter
@Entity
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String avatarPath;
    private String location;
    private String biography;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
    @OneToMany
    @JsonBackReference
    private List<Kweet> kweets;

    @ManyToMany
    private List<Profile> followers;
    @Transient
    private List<Profile> followees;

    public Profile() {
        this.followers = new ArrayList<Profile>();
        this.followees = new ArrayList<Profile>();
    }

    public Profile(String firstName, String lastName, String avatarPath, String location, String biography, Account account) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarPath = avatarPath;
        this.location = location;
        this.biography = biography;
        this.account = account;
    }

    //<editor-fold desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public List<Profile> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Profile> followers) {
        this.followers = followers;
    }

    public List<Profile> getFollowees() {
        return followees;
    }

    public void setFollowees(List<Profile> followees) {
        this.followees = followees;
    }
//</editor-fold>

    //<editor-fold desc="equals/hashCode">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile that = (Profile) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, account);
    }
    //</editor-fold>
}
