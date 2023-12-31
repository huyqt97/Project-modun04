package ra.webwalefashion.model.entity;

public class Brand {
    private int brandId;
    private String nameBrand;

    public Brand() {
    }

    public Brand(int brandId, String nameBrand) {
        this.brandId = brandId;
        this.nameBrand = nameBrand;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }
}
