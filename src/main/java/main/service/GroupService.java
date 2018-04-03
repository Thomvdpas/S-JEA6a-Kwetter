package main.service;

import com.mysql.jdbc.StringUtils;
import main.dao.GroupDao;
import main.dao.JPA;
import main.domain.UserGroup;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Thom van de Pas on 3-4-2018
 */
@Stateless
public class GroupService {

    @Inject
    @JPA
    private GroupDao groupDao;

    public GroupService() {
    }

    public UserGroup findByGroupName(String groupName) {
        if (!StringUtils.isNullOrEmpty(groupName)) {
            return this.groupDao.findByGroupName(groupName);
        }
        return null;
    }

    public UserGroup create(UserGroup userGroup) {
        return this.groupDao.create(userGroup);
    }
}
