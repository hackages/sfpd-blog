package be.sfpd.blog.resource;

import be.sfpd.blog.model.Comment;
import be.sfpd.blog.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

    // Manage to return Status code 201 for this endpoint
    @POST
    public Comment addComment(@PathParam("articleId") Long articleId, Comment comment) {
        return commentService.addComment(articleId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public void deleteComment(@PathParam("articleId") Long articleId, @PathParam("commentId") Long commentId) {
        commentService.removeComment(articleId, commentId);
    }

}
