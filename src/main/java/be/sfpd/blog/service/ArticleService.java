package be.sfpd.blog.service;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.repository.MockDatabase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleService {

    private final Map<Long, Article> articles = MockDatabase.getArticles();

    public ArticleService() {
        articles.put(1L, new Article(1L, LocalDateTime.now().minusDays(1), "Hello world"));
        articles.put(2L, new Article(2L, LocalDateTime.now().minusHours(4), "Hello Jersey"));
    }

    public List<Article> getArticles() {
        return new ArrayList<>(articles.values());
    }

    public Article getArticleById(Long id) {
        return articles.get(id);
    }

    public Article addArticle(Article article) {
        article.setId((long) (articles.size() + 1));
        article.setCreatedDate(LocalDateTime.now());
        articles.put(article.getId(), article);
        return article;
    }

    public Article updateArticle(Article article) {
        if (article.getId() <= 0 && articles.get(article.getId()) == null) {
            return null;
        }
        article.setCreatedDate(articles.get(article.getId()).getCreatedDate());
        articles.put(article.getId(), article);
        return article;
    }

    public void removeArticle(Long id) {
        articles.remove(id);
    }
}
