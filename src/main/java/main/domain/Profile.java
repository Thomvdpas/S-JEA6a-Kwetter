package main.domain;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Thom van de Pas on 27-2-2018
 */
//@Getter
//@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "profile.findByUsername", query = "SELECT p FROM Profile p WHERE p.account.username = :username")
})
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", orphanRemoval = true)
    private List<Kweet> kweets;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Kweet> mentionKweets;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "followings")
    private List<Profile> followers;

    @ManyToMany
    private List<Profile> followings;

    public Profile() {
        this.mentionKweets = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.followings = new ArrayList<>();
        this.kweets = new LinkedList<>();
    }

    public Profile(String firstName, String lastName) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Profile(String firstName, String lastName, Account account) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
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

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("name", this.getFullName())
                .add("biography", this.getBiography())
                .add("location", this.getLocation())
                .add("avatarPath", this.getAvatarPath())
                .build();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void addKweet(Kweet kweet) {
        this.kweets.add(kweet);
    }

    public void addFollowing(Profile following) {
        this.followings.add(following);
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

    public List<Profile> getFollowings() {
        return followings;
    }

    public void setFollowings(List<Profile> followings) {
        this.followings = followings;
    }

    public List<Kweet> getMentionKweets() {
        return mentionKweets;
    }

    public void setMentionKweets(List<Kweet> mentionKweets) {
        this.mentionKweets = mentionKweets;
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
