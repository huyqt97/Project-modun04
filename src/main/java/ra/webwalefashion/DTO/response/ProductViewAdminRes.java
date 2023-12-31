package ra.webwalefashion.DTO.response;

public class ProductViewAdminRes {
    private int productId;
    private String name;
    private String image;
    private String brand;
    private double price;
    private String categoryId;
    private String sizeId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
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

    public ProductViewAdminRes() {
    }

    public ProductViewAdminRes(int productId, String name, String image, String brand, double price, String categoryId, String sizeId, int stock, int sold) {
        this.productId = productId;
        this.name = name;
        this.image = image;
        this.brand = brand;
        this.price = price;
        this.categoryId = categoryId;
        this.sizeId = sizeId;
        this.stock = stock;
        this.sold = sold;
    }
}
