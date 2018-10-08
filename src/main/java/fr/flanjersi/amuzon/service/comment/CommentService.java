package fr.flanjersi.amuzon.service.comment;

import fr.flanjersi.amuzon.models.comment.Comment;
import fr.flanjersi.amuzon.models.product.Product;

import java.util.List;

public interface CommentService {

    /**
     * Add a comment
     * @param idProduct
     * @param comment
     */
    void addComment(int idProduct, Comment comment);

    /**
     * Get a comment
     * @param id id of product
     * @return
     */
    Comment getComment(int id);

    /**
     * Delete a comment
     * @param id
     */
    void removeComment(int id);

    /**
     * Get all comment of a product
     * @param idProduct
     * @return
     */
    List<Comment> getCommentOfProduct(int idProduct);

    /**
     * Increment one usefulvote comment
     * @param idComment
     */
    void incrementUsefulVote(int idComment);

    /**
     * Increment one not usefulvote comment
     * @param idComment
     */
    void incrementNotUsefulVote(int idComment);

}
