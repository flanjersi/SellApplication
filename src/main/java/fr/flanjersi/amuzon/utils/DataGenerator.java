package fr.flanjersi.amuzon.utils;

import com.thedeanda.lorem.LoremIpsum;
import fr.flanjersi.amuzon.models.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    private static LoremIpsum loremIpsum = LoremIpsum.getInstance();

    public static Product genereProduct(){
        List<String> categories = new ArrayList<>();
        categories.add("Computer");
        categories.add("Clothes");
        categories.add("Movies");


        Random random = new Random();

        Product product= new Product();

        product.setTitle(loremIpsum.getTitle(1));
        product.setCategory(categories.get(random.nextInt(categories.size())));
        product.setDescription(loremIpsum.getWords(1));

        return product;
    }

}
