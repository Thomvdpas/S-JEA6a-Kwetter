package domain;

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
public class UserProfile implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String avatarPath;
    private String location;
    private String biography;

    @OneToOne
    private UserAccount userAccount;
    @OneToMany
    private List<Kweet> kweets;
    @ManyToMany
    private List<UserProfile> followers;
    @Transient
    private List<UserProfile> followees;

    public UserProfile() {
        this.followers = new ArrayList<UserProfile>();
        this.followees = new ArrayList<UserProfile>();
    }

    public UserProfile(String firstName, String lastName, String avatarPath, String location, String biography, UserAccount userAccount) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarPath = avatarPath;
        this.location = location;
        this.biography = biography;
        this.userAccount = userAccount;
    }

    //<editor-fold desc="Getters/Setters">
    public Long getId() {
        return id;
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

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public List<UserProfile> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserProfile> followers) {
        this.followers = followers;
    }

    public List<UserProfile> getFollowees() {
        return followees;
    }

    public void setFollowees(List<UserProfile> followees) {
        this.followees = followees;
    }
//</editor-fold>

    //<editor-fold desc="equals/hashCode">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userAccount, that.userAccount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userAccount);
    }
    //</editor-fold>
}
