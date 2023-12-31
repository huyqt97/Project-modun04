package ra.webwalefashion.model.entity;

import java.sql.Date;

public class Order {
    private int order_id;
    private int user_id;
    private Date date;
    private String phone;
    private String address;
    private String nameUser;
    private int quantity;
    private double price;
    private boolean status;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Order() {
    }

    public Order(int order_id, int user_id, Date date, String phone, String address, String nameUser, int quantity, double price, boolean status) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.date = date;
        this.phone = phone;
        this.address = address;
        this.nameUser = nameUser;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }
}
