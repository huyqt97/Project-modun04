package ra.webwalefashion.DTO.response;

public class ProductViewShopRes {
    private int productId;
    private String name;
    private String image;
    private String brand;
    private String des;
    private double price;
    private String category;
    private String size;
    private int stock;
    private int sold;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public ProductViewShopRes() {
    }

    public ProductViewShopRes(int productId, String name, String image, String brand, String des, double price, String category, String size, int stock, int sold) {
        this.productId = productId;
        this.name = name;
        this.image = image;
        this.brand = brand;
        this.des = des;
        this.price = price;
        this.category = category;
        this.size = size;
        this.stock = stock;
        this.sold = sold;
    }
}
