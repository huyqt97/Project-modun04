package ra.webwalefashion.DTO.request;

public class UserUpdateReq {
    private int userId;
    private String email;
    private String fullName;
    private String avatar;
    private boolean sex;
    private int age;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserUpdateReq() {
    }

    public UserUpdateReq(int userId, String email, String fullName, String avatar, boolean sex, int age) {
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.avatar = avatar;
        this.sex = sex;
        this.age = age;
    }
}
