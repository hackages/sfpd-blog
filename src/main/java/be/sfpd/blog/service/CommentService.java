package be.sfpd.blog.service;

import be.sfpd.blog.model.Article;
import be.sfpd.blog.model.Comment;
import be.sfpd.blog.repository.MockDatabase;

import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService {

    private Map<Long, Article> articles = MockDatabase.getArticles();

    public List<Comment> getAllComments(long articleId) {
        Map<Long, Comment> comments = articles.get(articleId).getComments();
        return new ArrayList<>(comments.values());
    }

    public Comment addComment(Long articleId, Comment comment) {
        Map<Long, Comment> comments = articles.get(articleId).getComments();
        comment.setId(comments.size() + 1);
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(long articleId, Comment comment) {
        Map<Long, Comment> comments = articles.get(articleId).getComments();
        if (comment.getId() <= 0) {
            return null;
        }
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(long articleId, long commentId) {
        Map<Long, Comment> comments = articles.get(articleId).getComments();
        return comments.remove(commentId);
    }
}
