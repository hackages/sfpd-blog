package be.sfpd.blog.resource;

import be.sfpd.blog.model.Comment;
import be.sfpd.blog.service.CommentService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class CommentResource {

    CommentService commentService = new CommentService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getAllArticles(@PathParam("articleId") Long id) {
        return commentService.getAllComments(id);
    }

    @GET
    @Path("/{commentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment getCommentById(@PathParam("articleId") Long articleId, @PathParam("commentId") Long commentId) {
        return commentService.getCommentById(articleId, commentId);
    }

    // Implement the endpoint to create a comment

}
