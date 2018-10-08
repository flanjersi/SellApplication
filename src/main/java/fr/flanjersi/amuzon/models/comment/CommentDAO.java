package fr.flanjersi.amuzon.models.comment;

import fr.flanjersi.amuzon.models.product.Product;

import java.util.List;

public interface CommentDAO {

    /**
     * Add a comment
     * @param idProduct
     * @param comment
     */
    void add(int idProduct, Comment comment);

    /**
     * Update a comment
     * @param comment
     */
    void update(Comment comment);

    /**
     * Delete a comment
     * @param id
     */
    void delete(int id);

    /**
     * Find Comment by id
     * @param id
     * @return
     */
    Comment findById(int id);

    /**
     * Find comment by product
     * @param idProduct id of the product
     * @return
     */
    List<Comment> findByProduct(int idProduct);

    /**
     * Increment usefulVote stats
     */
    void incrementUsefulVote(int idComment);

    /**
     * Increment not useful stats
     */
    void incrementNotUsefulVote(int idComment);

    /**
     * De-increment usefulVote stats
     */
    void deIncrementUsefulVote(int idComment);

    /**
     * De-increment not useful stats
     */
    void deIncrementNotUsefulVote(int idComment);

    /**
     * AVG of rating of comments of product
     * @param idProduct
     */
    double avgRatingOfProduct(int idProduct);

}
