package fr.flanjersi.amuzon.service.comment;

import fr.flanjersi.amuzon.models.comment.Comment;
import fr.flanjersi.amuzon.models.comment.CommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceJDBC implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public void addComment(int idProduct, Comment comment) {
        commentDAO.add(idProduct, comment);
    }

    @Override
    public Comment getComment(int id) {
        return commentDAO.findById(id);
    }

    @Override
    public void removeComment(int id) {
        commentDAO.delete(id);
    }

    @Override
    public List<Comment> getCommentOfProduct(int idProduct) {
        List<Comment> comments = commentDAO.findByProduct(idProduct);

        comments = comments.stream().sorted((Comment c1, Comment c2) ->
                Integer.compare(
                        c2.getUsefulVote() - c2.getNotUsefulVote(),
                        c1.getUsefulVote() - c1.getNotUsefulVote()
                        )
        ).collect(Collectors.toList());

        return comments;
    }

    @Override
    public void incrementUsefulVote(int idComment) {
        commentDAO.incrementUsefulVote(idComment);
    }

    @Override
    public void incrementNotUsefulVote(int idComment) {
        commentDAO.incrementNotUsefulVote(idComment);
    }
}
