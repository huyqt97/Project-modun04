package ra.webwalefashion.model.entity;

public class Cart {
    private int id;
    private int userId;
    private int totalQuantity;
    private double totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Cart() {
    }

    public Cart(int id, int userId, int totalQuantity, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }
}
