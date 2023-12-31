package ra.webwalefashion.DTO.response;

public class UserViewAdminRes {
    private int userId;
    private String userName;
    private String email;
    private String fullName;
    private String avatar;
    private boolean sex;
    private boolean status;
    private int age;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserViewAdminRes() {

    }

    public UserViewAdminRes(int userId, String userName, String email, String fullName, String avatar, boolean sex, boolean status, int age) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.avatar = avatar;
        this.sex = sex;
        this.status = status;
        this.age = age;
    }
}
