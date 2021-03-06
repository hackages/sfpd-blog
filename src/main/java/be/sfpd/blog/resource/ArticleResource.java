package be.sfpd.blog.resource;


import be.sfpd.blog.exception.DataNotFoundException;
import be.sfpd.blog.model.Article;
import be.sfpd.blog.model.ErrorMessage;
import be.sfpd.blog.resource.bean.ArticleFilterBean;
import be.sfpd.blog.service.ArticleService;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
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
    public Response createArticle(Article article, @Context UriInfo uriInfo) throws URISyntaxException {
        Article newArticle = service.addArticle(article);
        String newId = String.valueOf(newArticle.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(newArticle).build();
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
    public Response getArticleById(@PathParam("id") Long articleId, @Context UriInfo uriInfo) {
        try {
            Article newArticle = service.getArticleById(articleId);


            newArticle.addLink(getUriForProfile(uriInfo, newArticle.getAuthor()), "authorProfile");

            Link self = Link.fromUri(uriInfo.getAbsolutePath()).rel("self").type("GET").build();

            URI commentsURI = uriInfo
                    .getBaseUriBuilder()
                    .path(ArticleResource.class)
                    .path(ArticleResource.class, "getCommentsByArticle")
                    .resolveTemplate("articleId", articleId)
                    .build();

            Link comments = Link.fromUri(commentsURI).type("GET").rel("comments").build();

            newArticle.addLink(self.getUri().toString(), self.getRel());
            newArticle.addLink(comments.getUri().toString(), comments.getRel());

            return Response.ok(newArticle).links(self, comments).build();
        } catch (DataNotFoundException ex) {
            ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), "SFPD_NOT_FOUND");
            return Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
        }
    }

    private String getUriForSelf(UriInfo uriInfo, Long articleId) {
        String uri = uriInfo
                .getBaseUriBuilder()
                .path(ArticleResource.class)
                .path(Long.toString(articleId))
                .build()
                .toString();
        return uri;
    }

    private String getUriForComments(UriInfo uriInfo, Long articleId) {
        String uri = uriInfo
                .getBaseUriBuilder()
                .path(ArticleResource.class)
                .path(ArticleResource.class, "getCommentsByArticle")
                .resolveTemplate("articleId", articleId)
                .build()
                .toString();
        return uri;
    }

    private String getUriForProfile(UriInfo uriInfo, String authorName) {
        String uri = uriInfo
                .getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(authorName)
                .build()
                .toString();
        return uri;
    }

    @Path("/{articleId}/comments")
    public CommentResource getCommentsByArticle(@PathParam("articleId") Long id) {
        return new CommentResource();
    }
    
}
