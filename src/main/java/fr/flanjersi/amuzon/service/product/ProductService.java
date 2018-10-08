package fr.flanjersi.amuzon.service.product;

import fr.flanjersi.amuzon.models.product.Product;

import java.util.List;

public interface ProductService {

    /**
     * Add a product
     * @param product
     */
    void addProduct(Product product);

    /**
     * Get a product
     * @param id id of product
     * @return
     */
    Product getProduct(int id);

    /**
     * Delete a product
     * @param id
     */
    void removeProduct(int id);

    /**
     * Get All products
     * @return
     */
    List<Product> getProducts();

    /**
     * Get All products who have a specific category
     * @param category
     * @return
     */
    List<Product> getCategoryProducts(String category);

    /**
     * Get The Nb add last products
     * @param nbProduct
     * @return
     */
    List<Product> getNLastAddProduct(int nbProduct);

    /**
     * Get the rating of the product :
     * AVG of rating of comments
     * @return
     */
    double getRating(int idProduct);

}
