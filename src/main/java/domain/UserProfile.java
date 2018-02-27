package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Thom van de Pas on 27-2-2018
 */
@Getter
@Setter
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
    @OneToMany
    private List<UserProfile> followers;
    @OneToMany
    private List<UserProfile> following;

    public UserProfile() {
    }

    public UserProfile(String firstName, String lastName, String avatarPath, String location, String biography, UserAccount userAccount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarPath = avatarPath;
        this.location = location;
        this.biography = biography;
        this.userAccount = userAccount;
    }

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
}
