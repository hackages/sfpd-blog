package be.sfpd.blog.service;

import be.sfpd.blog.exception.DataNotFoundException;
import be.sfpd.blog.exception.DatabaseException;
import be.sfpd.blog.model.Article;
import be.sfpd.blog.model.Comment;
import be.sfpd.blog.repository.MockDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService {

    private Map<Long, Article> articles = MockDatabase.getArticles();

    // Add exception for possible NPE
    public List<Comment> getAllComments(Long articleId) {
        Map<Long, Comment> comments = articles.get(articleId).getComments();
        return new ArrayList<>(comments.values());
    }

    public Comment getCommentById(Long articleId, Long commentId) {
        Article article = articles.get(articleId);
        if (article == null) {
            throw new DataNotFoundException("article is missing");
        }
        Map<Long, Comment> comments = article.getComments();
        if (comments == null) {
            throw new DatabaseException("Something wrong happen");
        }
        Comment comment = comments.get(commentId);
        if (comment == null){
            throw new DatabaseException("comment is missing");
        }
        return articles.get(articleId).getComments().get(commentId);
    }

    // Here also
    public Comment addComment(Long articleId, Comment comment) {
        Map<Long, Comment> comments = articles.get(articleId).getComments();
        comment.setId((long) (comments.size() + 1));
        comments.put(comment.getId(), comment);
        return comment;
    }

    // Here alse
    public void removeComment(Long articleId, Long commentId) {
        Map<Long, Comment> comments = articles.get(articleId).getComments();
        comments.remove(commentId);
    }
}
