package main.dao.jpa;

import main.dao.GenericDaoJPAImpl;
import main.dao.GroupDao;
import main.dao.JPA;
import main.domain.UserGroup;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * @author Thom van de Pas on 3-4-2018
 */
@Stateless
@JPA
public class GroupDaoJPAImpl extends GenericDaoJPAImpl<UserGroup> implements GroupDao {

    public GroupDaoJPAImpl() {
    }

    @Override
    public UserGroup findByGroupName(String groupName) {
        TypedQuery<UserGroup> query = getEntityManager().createNamedQuery("group.findByGroupName", UserGroup.class)
                .setParameter("groupName", groupName);

        return oneResult(query);
    }

    @Override
    public UserGroup findByAccount(String username) {
        TypedQuery<UserGroup> query = getEntityManager().createNamedQuery("group.findByAccount", UserGroup.class)
                .setParameter("username", username);

        return oneResult(query);
    }


}
