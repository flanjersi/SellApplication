package fr.flanjersi.amuzon.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.flanjersi.amuzon.models.product.Product;
import fr.flanjersi.amuzon.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class UploadFileController {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileController.class);

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ServletContext context;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getPage() {
        return "uploadfile";
    }


    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Map<String, Object> model) {
        if(file == null || file.getName().isEmpty() || file.getContentType().equals("application/json")){
            model.put("products", productService.getProducts() );
            model.put("lastProducts", productService.getNLastAddProduct(5));

            return "homepage";
        }


        List<Product> products = new ArrayList<>();

        try {
            JsonReader jsonReader = Json.createReader(file.getInputStream());

            JsonObject jsonObject = jsonReader.readObject();

            String category = jsonObject.getString("category");
            String title = jsonObject.getString("productTitle");
            String brand = jsonObject.getString("brand");

            Product product = new Product();
            product.setTitle(title);
            product.setCategory(category);
            product.setDescription(brand);

            productService.addProduct(product);


            jsonObject.getJsonArray("reviews");


            logger.info("Products Saved!");
        } catch (IOException e){
            logger.error("Unable to save Products: " + e.getMessage());
        }

        model.put("products", productService.getProducts() );
        model.put("lastProducts", productService.getNLastAddProduct(5));

        return "homepage";
    }

}
