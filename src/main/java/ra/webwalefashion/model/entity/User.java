package ra.webwalefashion.model.entity;

public class User {
//    user_id INT AUTO_INCREMENT PRIMARY KEY,
//    user_name VARCHAR(100) UNIQUE,
//    email VARCHAR(100) UNIQUE,
//    fullname VARCHAR(100),
//    password VARCHAR(255),
//    avatar TEXT,
//    sex bit,
//    role_id INT default 2,
//    status BIT DEFAULT 1,
//    age INT CHECK (age >= 1),
//    foreign key (role_id) references roles(role_id)
    private int userId;
    private String userName;
    private String email;
    private String fullName;
    private String password;
    private String avatar;
    private boolean sex;
    private int role_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
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

    public User() {
    }

    public User(int userId, String userName, String email, String fullName, String password, String avatar, boolean sex, int role_id, boolean status, int age) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.avatar = avatar;
        this.sex = sex;
        this.role_id = role_id;
        this.status = status;
        this.age = age;
    }
}
