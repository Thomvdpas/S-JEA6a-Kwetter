package main.service;

import com.mysql.jdbc.StringUtils;
import main.dao.GroupDao;
import main.dao.JPA;
import main.domain.Account;
import main.domain.UserGroup;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Thom van de Pas on 3-4-2018
 */
@Stateless
public class UserGroupService {

    @Inject
    @JPA
    private GroupDao groupDao;

    public UserGroupService() {
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

    public void update(UserGroup userGroup) {
        if (userGroup != null) {
            this.groupDao.update(userGroup);
        }
    }

    public List<UserGroup> findAll() {
        return this.groupDao.findAll();
    }

    public List<UserGroup> findGroupsForAccount(Account foundAccount) {
        return this.groupDao.findByAccount(foundAccount.getUsername());
    }
}
