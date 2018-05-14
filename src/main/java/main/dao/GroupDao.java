package main.dao;

import main.domain.UserGroup;

import java.util.List;

/**
 * @author Thom van de Pas on 3-4-2018
 */
public interface GroupDao extends GenericDao<UserGroup> {

    UserGroup findByGroupName(String groupName);

    List<UserGroup> findByAccount(String username);
}