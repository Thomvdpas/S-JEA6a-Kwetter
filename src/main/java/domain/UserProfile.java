package domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author Thom van de Pas on 27-2-2018
 */
public class UserProfile implements Serializable {

    private Long id;

    private String avatarPath;
    private String firstName;
    private String lastName;
    private String location;
    private String biography;
    private UserAccount userAccount;
    private List<Kweet> kweets;
    private List<UserProfile> followers;
    private List<UserProfile> following;
}
