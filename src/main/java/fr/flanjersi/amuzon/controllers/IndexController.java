package fr.flanjersi.amuzon.controllers;

import fr.flanjersi.amuzon.models.product.Product;
import fr.flanjersi.amuzon.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ServletContext context;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(Map<String, Object> model){
        String sessionUser = (String) httpSession.getAttribute("user");

        model.put("products", productService.getProducts() );
        model.put("lastProducts", productService.getNLastAddProduct(5));

        logger.info("session user = " + sessionUser);

        return "homepage";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam(value = "title") String title,
                             @RequestParam(value = "description") String description,
                             @RequestParam(value = "category") String category,
                             @RequestParam(value = "brand") String brand,
                             @RequestParam(value = "price") double price){

        Product product = new Product();
        product.setTitle(title);
        product.setCategory(category);
        product.setDescription(description);
        product.setBrand(brand);
        product.setPrice(price);

        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        product.setDate(date);

        productService.addProduct(product);

        return "redirect:";
    }


    @PostMapping("/remove")
    public String remove(@RequestParam(value = "productId") int id, Map<String, Object> model){

        productService.removeProduct(id);

        return "redirect:";
    }

    @PostMapping("/category")
    public String category(@RequestParam("name") String nameCategory, Map<String, Object> model){

        if(nameCategory.equals("all")){
            model.put("products", productService.getProducts());
        }
        else {
            model.put("products", productService.getCategoryProducts(nameCategory));
        }

        model.put("lastProducts", productService.getNLastAddProduct(5));

        return "homepage";
    }


}
