package ra.webwalefashion.DTO.request;

import javax.validation.constraints.NotEmpty;
public class UserLoginReq {
    @NotEmpty(message = "Không được để trống!")
    private String userName;
    @NotEmpty(message = "Không được để trống!")
    private String password;

    public UserLoginReq() {
    }

    public UserLoginReq(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
