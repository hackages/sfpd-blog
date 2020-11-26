package be.sfpd.blog.service;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.repository.MockDatabase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArticleService {

    private Map<Long, Article> articles = MockDatabase.getArticles();

    public ArticleService() {
        Article article1 = new Article(1L, LocalDateTime.now(), "Hello world");
        Article article2 = new Article(2L, LocalDateTime.now().minusYears(1), "Hello Jersey");
        articles.put(1L, article1);
        articles.put(2L, article2);
    }

    public List<Article> getArticles() {
        return new ArrayList<>(articles.values());
    }

    public List<Article> getPaginatedArticles(int offset, int limit, List<Article> articlesToPaginate) {
        if (articlesToPaginate == null || articlesToPaginate.isEmpty()) {
            articlesToPaginate = new ArrayList<>();
            articlesToPaginate.addAll(articles.values());
        }
        if (offset + limit > articlesToPaginate.size()) {
            if (offset <= articlesToPaginate.size() ) {
                return articlesToPaginate.subList(offset, articlesToPaginate.size());
            }
            return new ArrayList<>();
        }
        return articlesToPaginate.subList(offset, offset + limit);
    }

    public List<Article> getArticlesByYear(int year) {
        List<Article> articleList = new ArrayList<>(articles.values());
        return articleList
                .stream()
                .filter(article -> article.getCreatedDate().getYear() == year)
                .collect(Collectors.toList());
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
