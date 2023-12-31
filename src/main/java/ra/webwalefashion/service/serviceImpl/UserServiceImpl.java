package ra.webwalefashion.service.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webwalefashion.DTO.request.UserLoginReq;
import ra.webwalefashion.DTO.request.UserRegisterReq;
import ra.webwalefashion.DTO.response.UserLoginRes;
import ra.webwalefashion.DTO.response.UserViewAdminRes;
import ra.webwalefashion.model.DAO.UserDAO;
import ra.webwalefashion.model.entity.User;
import ra.webwalefashion.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public void saveRegister(UserRegisterReq userRegisterReq) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userRegisterReq, User.class);
        userDAO.save(user);
    }

    @Override
    public UserLoginRes login(UserLoginReq userLoginReq) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userLoginReq, User.class);
        if (userDAO.findByLogin(user) != null) {
            return modelMapper.map(userDAO.findByLogin(user), UserLoginRes.class);
        }
        return null;
    }

    @Override
    public boolean findByUserName(String userName) {
        return userDAO.findByUserName(userName);
    }

    @Override
    public List<UserViewAdminRes> findAll() {
        List<UserViewAdminRes> userViewAdminRes = new ArrayList<>();
        for (User u : userDAO.findALl()) {
            ModelMapper modelMapper = new ModelMapper();
            userViewAdminRes.add(modelMapper.map(u, UserViewAdminRes.class));
        }
        return userViewAdminRes;
    }

    @Override
    public void blockUser(int id) {
        User user = userDAO.findById(id);
        if (user != null) {
            user.setStatus(false);
            userDAO.save(user);
        }
    }

    @Override
    public void unlockUser(int id) {
        User user = userDAO.findById(id);
        if (user != null) {
            user.setStatus(true);
            userDAO.save(user);
        }
    }

    @Override
    public List<UserViewAdminRes> findByFullNameOrUserName(String text) {
        return userDAO.findByFullNameOrUserName(text);
    }
}
