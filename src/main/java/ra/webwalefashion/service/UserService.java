package ra.webwalefashion.service;
import ra.webwalefashion.DTO.request.UserLoginReq;
import ra.webwalefashion.DTO.request.UserRegisterReq;
import ra.webwalefashion.DTO.response.UserLoginRes;
import ra.webwalefashion.DTO.response.UserViewAdminRes;

import java.util.List;

public interface UserService{
    void saveRegister(UserRegisterReq userRegisterReq);
    UserLoginRes login(UserLoginReq userLoginReq);
    boolean findByUserName(String userName);
    List<UserViewAdminRes> findAll();
    void blockUser(int id);
    void unlockUser(int id);
    List<UserViewAdminRes> findByFullNameOrUserName(String text);
}
