package be.sfpd.blog.resource;

import be.sfpd.blog.model.Comment;
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
    public Comment getCommentById(@PathParam("articleId") Long articleId, @PathParam("commentId") Long commentId) {
        return commentService.getCommentById(articleId, commentId);
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
