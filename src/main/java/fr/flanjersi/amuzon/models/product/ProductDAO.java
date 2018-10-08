package fr.flanjersi.amuzon.models.product;

import java.util.List;

/**
 * Product DAO interface of Product Bean
 */
public interface ProductDAO {

    /**
     * Add a product
     * @param product
     */
    void add(Product product);

    /**
     * Update a product
     * @param product
     */
    void update(Product product);

    /**
     * Delete a product
     * @param product
     */
    void delete(Product product);

    /**
     * Get all products
     * @return
     */
    List<Product> findAll();

    /**
     * Find product by id
     * @param id
     * @return
     */
    Product findById(int id);

    /**
     * Find products by their category
     * @param category
     * @return
     */
    List<Product> findByCategory(String category);

    List<Product> findNLastAddProduct(int nbProduct);
}
