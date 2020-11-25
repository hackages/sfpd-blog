package be.sfpd.blog.repository;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class MockDatabase {

    private static final Map<Long, Article> articles = new HashMap<>();
    private static final Map<String, Profile> profiles = new HashMap<>();

    public static Map<Long, Article> getArticles() {
        return articles;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

}
