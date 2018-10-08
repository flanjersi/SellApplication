package fr.flanjersi.amuzon.models.product;

public class Product {

    /**
     * Id of the product ( Database only )
     */
    private int id;


    /**
     * Title of the product
     */
    private String title;

    /**
     * Description of the product
     */
    private String description;

    /**
     * Brand of the product
     */
    private String brand;

    /**
     * Category of the product
     */
    private String category;

    /**
     * Price of the product
     */
    private double price;

    /**
     * Img of the product
     */
    private String img;

    /**
     * Date when product was insert
     */
    private String date;


    public Product() { }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

