package be.sfpd.blog.resource;


import be.sfpd.blog.model.Article;
import be.sfpd.blog.service.ArticleService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("articles")
public class ArticleResource {

    ArticleService service = new ArticleService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getAllArticle() {
        return service.getArticles();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Article createArticle(Article article) {
        return service.addArticle(article);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Article getArticleById(@PathParam("id") Long articleId) {
        return service.getArticleById(articleId);
    }


}
