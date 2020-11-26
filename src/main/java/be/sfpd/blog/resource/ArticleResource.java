package be.sfpd.blog.resource;


import be.sfpd.blog.model.Article;
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
    public List<Article> getAllArticle(@QueryParam("offset") int offset, @QueryParam("limit") int limit, @QueryParam("year") int year) {
        List<Article> articlesByYear = new ArrayList<>();
        if (year > 0) {
            articlesByYear.addAll(service.getArticlesByYear(year));
        } else {
            articlesByYear.addAll(service.getArticles());
        }
        if (offset >= 0 && limit > 0) {
            return service.getPaginatedArticles(offset, limit, articlesByYear);
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
    
}
