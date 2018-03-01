package dao.collection;

import dao.KweetDao;
import dao.ProfileDao;
import domain.Kweet;
import domain.Profile;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Thom van de Pas on 1-3-2018
 */
@Stateless
@Default
public class ProfileDaoColl implements ProfileDao {

    CopyOnWriteArrayList<Profile> profiles = new CopyOnWriteArrayList<Profile>();

    public Profile create(Profile profile) {
        profiles.add(profile);
        return profile;
    }

    public Profile update(Profile profile) {
        return null;
    }

    public void delete(Long id) {
        Profile profile = findById(id);
        if (profile != null) {
            profiles.remove(profile);
        }
    }

    public Profile findById(Long id) {
        for (Profile profile : profiles) {
            if (profile.getId().equals(id)) {
                return profile;
            }
        }
        return null;
    }
}
