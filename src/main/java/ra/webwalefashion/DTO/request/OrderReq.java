package ra.webwalefashion.DTO.request;

import java.sql.Date;

public class OrderReq {
    private int user_id;
    private String phone;
    private String address;
    private String nameUser;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public OrderReq() {
    }

    public OrderReq(int user_id, String phone, String address, String nameUser) {
        this.user_id = user_id;
        this.phone = phone;
        this.address = address;
        this.nameUser = nameUser;
    }
}
