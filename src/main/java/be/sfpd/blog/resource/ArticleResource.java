package be.sfpd.blog.resource;


import be.sfpd.blog.model.Article;
import be.sfpd.blog.resource.bean.ArticleFilterBean;
import be.sfpd.blog.service.ArticleService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("articles")
@Produces(MediaType.APPLICATION_JSON)
public class ArticleResource {

    ArticleService service = new ArticleService();

    @GET
    public List<Article> getAllArticle(@BeanParam ArticleFilterBean articleFilterBean) {
        List<Article> articlesByYear = new ArrayList<>();
        System.out.println("This is my token: " + articleFilterBean.getToken());
        System.out.println("I love cookies: " + articleFilterBean.getPreferences());
        if (articleFilterBean.getYear() > 0) {
            articlesByYear.addAll(service.getArticlesByYear(articleFilterBean.getYear()));
        } else {
            articlesByYear.addAll(service.getArticles());
        }
        if (articleFilterBean.getOffset() >= 0 && articleFilterBean.getLimit() > 0) {
            return service.getPaginatedArticles(articleFilterBean.getOffset(), articleFilterBean.getLimit(), articlesByYear);
        }
        return articlesByYear;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Article createArticle(Article article) {
        return service.addArticle(article);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Article updateArticle(@PathParam("id") Long articleId, Article article) {
        article.setId(articleId);
        return service.updateArticle(article);
    }

    @DELETE
    @Path("/{id}")
    public void removeArticle(@PathParam("id") Long articleId) {
        service.removeArticle(articleId);
    }

    @GET
    @Path("/{id}")
    public Article getArticleById(@PathParam("id") Long articleId) {
        return service.getArticleById(articleId);
    }

    @Path("/{articleId}/comments")
    public CommentResource getCommentsByArticle(@PathParam("articleId") Long id) {
        return new CommentResource();
    }
    
}
