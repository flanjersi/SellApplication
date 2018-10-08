package fr.flanjersi.amuzon.service.product;

import fr.flanjersi.amuzon.models.comment.CommentDAO;
import fr.flanjersi.amuzon.models.product.Product;
import fr.flanjersi.amuzon.models.product.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceJDBC implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public void addProduct(Product product) {
        productDAO.add(product);
    }

    @Override
    public Product getProduct(int id) {
        return productDAO.findById(id);
    }

    @Override
    public void removeProduct(int id) {
        Product product = new Product();
        product.setId(id);

        productDAO.delete(product);
    }

    @Override
    public List<Product> getProducts() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> getCategoryProducts(String category) {
        return productDAO.findByCategory(category);
    }

    @Override
    public List<Product> getNLastAddProduct(int nbProduct) {
        return productDAO.findNLastAddProduct(nbProduct);
    }

    @Override
    public double getRating(int idProduct) {
        return commentDAO.avgRatingOfProduct(idProduct);
    }
}


