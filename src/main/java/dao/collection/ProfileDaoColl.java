package dao.collection;

import dao.KweetDao;
import dao.ProfileDao;
import domain.Kweet;
import domain.Profile;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.List;
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

    public void delete(Profile profile) {
        for (Profile foundProfile : profiles) {
            if (foundProfile.equals(profile)) {
                profiles.remove(foundProfile);
            }
        }
    }
    public void deleteById(Long id) {
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

    public List<Profile> findAll() {
        return profiles;
    }
}
