package be.sfpd.blog.service;

import be.sfpd.blog.model.Profile;
import be.sfpd.blog.repository.MockDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {

    private Map<String, Profile> profiles = MockDatabase.getProfile();

    public ProfileService() {
        Profile profile = new Profile("ghost", 37);
        profiles.put("ghost", profile);
    }

    public List<Profile> getProfiles() {
        return new ArrayList(profiles.values());
    }

    public Profile addProfile(Profile profile) {
        if (profiles.get(profile.getName()) != null) {
            return null;
        }
        profiles.put(profile.getName(), profile);
        return profile;
    }

    public Profile getProfileByName(String name) {
        return profiles.get(name);
    }

}
