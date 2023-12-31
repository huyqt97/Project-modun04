package ra.webwalefashion.DTO.response;

public class CartItemViewUserRes {
    private int id;
    private String productImage;
    private String productName;
    private int stock;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CartItemViewUserRes() {
    }

    public CartItemViewUserRes(int id, String productImage, String productName, int stock, double price) {
        this.id = id;
        this.productImage = productImage;
        this.productName = productName;
        this.stock = stock;
        this.price = price;
    }
}
