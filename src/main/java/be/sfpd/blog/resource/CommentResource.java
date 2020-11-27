package be.sfpd.blog.resource;

import be.sfpd.blog.exception.DataNotFoundException;
import be.sfpd.blog.exception.DatabaseException;
import be.sfpd.blog.model.Comment;
import be.sfpd.blog.model.ErrorMessage;
import be.sfpd.blog.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    CommentService commentService = new CommentService();

    @GET
    public List<Comment> getAllArticles(@PathParam("articleId") Long id) {
        return commentService.getAllComments(id);
    }

    @GET
    @Path("/{commentId}")
    public Response getCommentById(@PathParam("articleId") Long articleId, @PathParam("commentId") Long commentId) {
        try{
            Comment newComment = commentService.getCommentById(articleId, commentId);
            return Response.ok(newComment).build();
        } catch (DataNotFoundException ex) {
            ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), "SFPD_NOT_FOUND");
            Response response = Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
            throw new NotFoundException(response);
            //throw new WebApplicationException(ex.getMessage(), response);
        } catch (DatabaseException ex) {
            ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), "SFPD_What");
            Response response = Response.serverError().entity(errorMessage).build();
            throw new ServerErrorException(response);
        }
    }

    @POST
    public Response addComment(@PathParam("articleId") Long articleId, Comment comment, @Context UriInfo uriInfo) {
        Comment newComment = commentService.addComment(articleId, comment);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(comment.getId())).build();
        return Response.created(uri).entity(newComment).build();
    }

    @DELETE
    @Path("/{commentId}")
    public void deleteComment(@PathParam("articleId") Long articleId, @PathParam("commentId") Long commentId) {
        commentService.removeComment(articleId, commentId);
    }

}
