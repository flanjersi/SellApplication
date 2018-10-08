package fr.flanjersi.amuzon.controllers.product;

import fr.flanjersi.amuzon.models.comment.Comment;
import fr.flanjersi.amuzon.service.comment.CommentService;
import fr.flanjersi.amuzon.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ServletContext context;

    @Autowired
    private ProductService productService;

    @Autowired
    private CommentService commentService;


    @GetMapping("{id}")
    public String showProduct(@PathVariable("id") int id, Map<String, Object> model){
        model = constructModelProductJSP(id, model);

        return "productpage";
    }

    @PostMapping("/addcomment")
    public String addComment(@RequestParam("rating") int rating, @RequestParam("productId") int productId, @RequestParam("comment") String text, Map<String, Object> model){
        if(!text.isEmpty()){

            Comment comment = new Comment();
            comment.setComment(text);
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            comment.setDate(date);
            comment.setRating(rating);


            commentService.addComment(productId, comment);
        }

        model = constructModelProductJSP(productId, model);

        return "redirect:" + productId;
    }

    @PostMapping("/removecomment")
    public String addComment(@RequestParam("commentId") int comentId, @RequestParam("productId") int productId, Map<String, Object> model){

        commentService.removeComment(comentId);

        model = constructModelProductJSP(productId, model);

        return "redirect:" + productId;
    }

    @PostMapping("/incrementUsefulVote")
    public String incrementUsefulVote(@RequestParam("commentId") int comentId, @RequestParam("productId") int productId, Map<String, Object> model){

        commentService.incrementUsefulVote(comentId);

        model = constructModelProductJSP(productId, model);

        return "redirect:" + productId;
    }

    @PostMapping("/incrementNotUsefulVote")
    public String incrementNotUsefulVote(@RequestParam("commentId") int comentId, @RequestParam("productId") int productId, Map<String, Object> model){

        commentService.incrementNotUsefulVote(comentId);

        model = constructModelProductJSP(productId, model);

        return "redirect:" + productId;
    }

    public Map<String, Object> constructModelProductJSP(int productId, Map<String, Object> model){
        model.put("product", productService.getProduct(productId));
        model.put("comments", commentService.getCommentOfProduct(productId));
        model.put("rating", productService.getRating(productId));

        return model;
    }



}
