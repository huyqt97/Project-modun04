package ra.webwalefashion.model.entity;

public class Size {
    private  int sizeId;
    private String sizeName;

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Size() {
    }

    public Size(int sizeId, String sizeName) {
        this.sizeId = sizeId;
        this.sizeName = sizeName;
    }
}
