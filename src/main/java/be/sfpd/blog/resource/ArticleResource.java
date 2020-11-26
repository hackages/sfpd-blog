package be.sfpd.blog.resource;


import be.sfpd.blog.model.Article;
import be.sfpd.blog.service.ArticleService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("articles")
@Produces(MediaType.APPLICATION_JSON)
public class ArticleResource {

    private final ArticleService service = new ArticleService();

    @GET
    public List<Article> getAllArticle(@QueryParam("offset") int offset, @QueryParam("limit") int limit, @QueryParam("year") int year) {

        return service.getArticles();
    }

    @GET
    @Path("/{id}")
    public Article getArticleById(@PathParam("id") Long articleId) {
        return service.getArticleById(articleId);
    }

    @POST
    public Article createArticle(Article article) {
        return service.addArticle(article);
    }

    @PUT
    @Path("/{articleId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Article updateArticle(@PathParam("articleId") Long id, Article article) {
        article.setId(id);
        return service.updateArticle(article);
    }

    @DELETE
    @Path("/{articleId}")
    public void deleteArticle(@PathParam("articleId") Long id) {
        service.removeArticle(id);
    }


}
