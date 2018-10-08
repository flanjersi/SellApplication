package fr.flanjersi.amuzon.models.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void add(int idProduct, Comment comment) {
        String rSQL = "INSERT INTO comments (id_product, comment, date, rating, USEFUL_VOTE, NOT_USEFUL_VOTE) VALUES (?, ?, ?, ?, 0, 0)";

        jdbcTemplate.update(rSQL, idProduct, comment.getComment(), comment.getDate(), comment.getRating());
    }

    @Override
    public void update(Comment comment) {
        String rSQL = "UPDATE comments SET comment = ? WHERE id = ?";

        jdbcTemplate.update(rSQL, comment.getComment(), comment.getId());
    }

    @Override
    public void delete(int id) {
        String rSQL = "DELETE FROM comments WHERE id = ?";

        jdbcTemplate.update(rSQL, id);
    }

    @Override
    public Comment findById(int id) {
        String rSQL = "SELECT * FROM COMMENTS WHERE ID = ?";

        return jdbcTemplate.queryForObject(rSQL, new Object[]{ id }, new BeanPropertyRowMapper<>(Comment.class));
    }

    @Override
    public List<Comment> findByProduct(int idProduct) {
        String rSQL = "SELECT * FROM COMMENTS WHERE ID_PRODUCT = ?";

        return jdbcTemplate.query(rSQL, new Object[]{ idProduct }, new BeanPropertyRowMapper<>(Comment.class));
    }

    @Override
    public double avgRatingOfProduct(int idProduct) {
        String rSQL = "SELECT AVG(RATING) FROM COMMENTS WHERE ID_PRODUCT = ?";

        Double result = jdbcTemplate.queryForObject(rSQL, new Object[]{ idProduct }, Double.class);

        return result == null ? 0 : result;
    }

    @Override
    public void incrementUsefulVote(int idComment) {
        String rSQL = "UPDATE comments SET USEFUL_VOTE = USEFUL_VOTE + 1 WHERE id = ?";

        jdbcTemplate.update(rSQL, idComment);

    }

    @Override
    public void incrementNotUsefulVote(int idComment) {
        String rSQL = "UPDATE comments SET NOT_USEFUL_VOTE = NOT_USEFUL_VOTE + 1 WHERE id = ?";

        jdbcTemplate.update(rSQL, idComment);
    }

    @Override
    public void deIncrementUsefulVote(int idComment) {
        String rSQL = "UPDATE comments SET USEFUL_VOTE = USEFUL_VOTE - 1 WHERE id = ?";

        jdbcTemplate.update(rSQL, idComment);
    }

    @Override
    public void deIncrementNotUsefulVote(int idComment) {
        String rSQL = "UPDATE comments SET NOT_USEFUL_VOTE = NOT_USEFUL_VOTE - 1 WHERE id = ?";

        jdbcTemplate.update(rSQL, idComment);
    }
}
