package ra.webwalefashion.DTO.response;

import org.springframework.web.multipart.MultipartFile;

public class ProductUpdateRes {
    private String name;
    private MultipartFile image;
    private int brand;
    private String des;
    private double price;
    private int categoryId;
    private int sizeId;
    private int stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ProductUpdateRes() {
    }

    public ProductUpdateRes(String name, MultipartFile image, int brand, String des, double price, int categoryId, int sizeId, int stock) {
        this.name = name;
        this.image = image;
        this.brand = brand;
        this.des = des;
        this.price = price;
        this.categoryId = categoryId;
        this.sizeId = sizeId;
        this.stock = stock;
    }
}
