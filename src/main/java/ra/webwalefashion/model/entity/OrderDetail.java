package ra.webwalefashion.model.entity;

public class OrderDetail {
    private int id;
    private int orderId;
    private String productName;
    private String productSize;
    private String productCategory;
    private String productBrand;
    private int quantity;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderDetail() {
    }

    public OrderDetail(int id, int orderId, String productName, String productSize, String productCategory, String productBrand, int quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.productName = productName;
        this.productSize = productSize;
        this.productCategory = productCategory;
        this.productBrand = productBrand;
        this.quantity = quantity;
        this.price = price;
    }
}
