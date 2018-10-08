package fr.flanjersi.amuzon.models.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void add(Product product) {
        String rSQL = "INSERT INTO products (TITLE, category, description, img, date, BRAND, price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(rSQL, product.getTitle(), product.getCategory(), product.getDescription(),
                product.getImg(), product.getDate(), product.getBrand(), product.getPrice());
    }

    @Override
    public void update(Product product) {
        String rSQL = "UPDATE products SET TITLE = ?, category = ?, description = ?, img = ?, date = ?, BRAND = ?, PRICE = ? WHERE id = ?";

        jdbcTemplate.update(rSQL, product.getTitle(), product.getCategory(), product.getDescription(),
                product.getImg(), product.getDate(), product.getBrand(), product.getPrice(), product.getId());
    }

    @Override
    public void delete(Product product) {
        String rSQL = "DELETE FROM products WHERE id = ?";

        jdbcTemplate.update(rSQL, product.getId());
    }

    @Override
    public List<Product> findAll() {
        String rSQL = "SELECT * FROM products";

        RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);

        return jdbcTemplate.query(rSQL, rowMapper);
    }

    @Override
    public Product findById(int id) {
        String rSQL = "SELECT * FROM products WHERE id = ?";

        return jdbcTemplate.queryForObject(rSQL, new Object[]{ id }, new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public List<Product> findByCategory(String category) {
        String rSQL = "SELECT * FROM products WHERE category = ?";

        return jdbcTemplate.query(rSQL, new Object[] { category }, new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public List<Product> findNLastAddProduct(int nbProduct) {
        String rSQL = "SELECT * FROM products ORDER BY date DESC LIMIT ?";

        return jdbcTemplate.query(rSQL, new Object[] { nbProduct }, new BeanPropertyRowMapper<>(Product.class));
    }
}
