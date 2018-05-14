package main.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thom van de Pas on 21-3-2018
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "group.findByGroupName", query = "SELECT u FROM UserGroup u WHERE u.groupName = :groupName"),
        @NamedQuery(name = "group.findByAccount",
                query = "SELECT ug FROM UserGroup ug JOIN ug.accounts uga WHERE uga.username = :username")
})

public class UserGroup implements Serializable {

    public static final String ADMIN_GROUP = "AdminGroup", MODERTOR_GROUP = "ModeratorGroup", USER_GROUP = "UserGroup";

    @Id
    private String groupName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usergroup_user",
            joinColumns = @JoinColumn(name = "groupName",
                    referencedColumnName = "groupName"),
            inverseJoinColumns = @JoinColumn(name = "username",
                    referencedColumnName = "username"))
    private List<Account> accounts;


    public UserGroup() {
        this.accounts = new ArrayList<>();
    }

    public UserGroup(String groupName) {
        this();
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public String toString() {
        return this.getGroupName();
    }
}
