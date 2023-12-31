package ra.webwalefashion.DTO.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegisterReq {
    @Size(min = 6, max = 18, message = "Tên tài khoản phải có (6 > 18) ký tự!")
    private String userName;
    @NotEmpty(message = "Không được để trống!")
    @Email(message = "Sai định dạng email!")
    private String email;
    @NotEmpty(message = "Không được để trống!")
    private String fullName;
    @Size(min = 6, max = 18, message = "Mật khẩu phải có (6 > 18) ký tự!")
    private String password;
    private int age;
    private boolean sex;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public UserRegisterReq() {
    }

    public UserRegisterReq(String userName, String email, String fullName, String password, int age, boolean sex) {
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.age = age;
        this.sex = sex;
    }
}
