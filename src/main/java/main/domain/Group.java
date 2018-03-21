package main.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Thom van de Pas on 21-3-2018
 */
@Entity
public class Group implements Serializable{

    public static final String ADMIN_GROUP="AdminGroup", MODERTOR_GROUP="ModeratorGroup", USER_GROUP="UserGroup";

    @Id
    private String groupName;

    public Group() {}

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
