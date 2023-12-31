package ra.webwalefashion.model.DAO;
import ra.webwalefashion.DTO.response.UserViewAdminRes;
import ra.webwalefashion.model.entity.User;

import java.util.List;

public interface UserDAO extends IGenericDAO<User,Integer>{
    User findByLogin(User user);
    boolean findByUserName(String userName);
    List<UserViewAdminRes> findByFullNameOrUserName(String text);
}
