package be.sfpd.blog.service;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.repository.MockDatabase;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArticleService {

    private Map<Long, Article> articles = MockDatabase.getArticles();

    public ArticleService() {
        Article article1 = new Article(1L, new Date(), "Hello world");
        Article article2 = new Article(2L, new Date(), "Hello Jersey");
        articles.put(1L, article1);
        articles.put(2L, article2);
    }

    public List<Article> getArticles() {
        return articles.values().stream().collect(Collectors.toList());
    }

    public Article getArticleById(Long id) {
        return articles.get(id);
    }

    public Article addArticle(Article article) {
        article.setId((long) (articles.size() + 1));
        article.setCreatedDate(new Date());
        articles.put(article.getId(), article);
        return article;
    }

    public Article updateArticle(Article article) {
        if (article.getId() <= 0 || articles.get(article.getId()) == null) {
            return null;
        }
        Article dbArticle = articles.get(article.getId());
        article.setCreatedDate(dbArticle.getCreatedDate());
        articles.put(article.getId(), article);
        return article;
    }

    public void removeArticle(Long id) {
        articles.remove(id);
    }
}
