package ra.webwalefashion.model.DAO.impl;

import org.springframework.stereotype.Repository;
import ra.webwalefashion.DTO.response.UserViewAdminRes;
import ra.webwalefashion.model.DAO.UserDAO;
import ra.webwalefashion.model.entity.User;
import ra.webwalefashion.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> findALl() {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        List<User> userList = new ArrayList<>();
        try {
            callSt = conn.prepareCall("{call pro_find_all_user()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUserName(rs.getString("user_name"));
                u.setEmail(rs.getString("email"));
                u.setFullName(rs.getString("fullname"));
                u.setPassword(rs.getString("password"));
                u.setAvatar(rs.getString("avatar"));
                u.setSex(rs.getBoolean("sex"));
                u.setRole_id(rs.getInt("role_id"));
                u.setStatus(rs.getBoolean("status"));
                u.setAge(rs.getInt("age"));
                userList.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return userList;
    }

    @Override
    public User findById(Integer integer) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        User user = null;
        try {
            callSt = conn.prepareCall("{call pro_find_by_id_user(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setFullName(rs.getString("fullname"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setSex(rs.getBoolean("sex"));
                user.setRole_id(rs.getInt("role_id"));
                user.setStatus(rs.getBoolean("status"));
                user.setAge(rs.getInt("age"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return user;
    }

    @Override
    public void save(User user) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        try {
            if (user.getUserId() == 0) {
                // thêm mới
                callSt = conn.prepareCall("{call pro_save_user_register(?,?,?,?,?,?)}");
                callSt.setString(1, user.getEmail());
                callSt.setString(2, user.getUserName());
                callSt.setString(3, user.getPassword());
                callSt.setString(4, user.getFullName());
                callSt.setBoolean(5, user.isSex());
                callSt.setInt(6, user.getAge());

                callSt.executeUpdate();
            } else {
                callSt = conn.prepareCall("{call pro_update_user(?,?,?,?,?,?,?)}");
                callSt.setInt(1, user.getUserId());
                callSt.setString(2, user.getEmail());
                callSt.setString(3, user.getFullName());
                callSt.setString(4, user.getAvatar());
                callSt.setBoolean(5, user.isSex());
                callSt.setInt(6, user.getAge());
                callSt.setBoolean(7, user.isStatus());
                callSt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void delete(Integer integer) {
    }

    @Override
    public User findByLogin(User  user) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        User user1 = null;
        try {
            callSt = conn.prepareCall("{call pro_find_by_login(?,?)}");
            callSt.setString(1, user.getUserName());
            callSt.setString(2, user.getPassword());
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                user1 = new User();
                user1.setUserId(resultSet.getInt("user_id"));
                user1.setUserName(resultSet.getString("user_name"));
                user1.setEmail(resultSet.getString("email"));
                user1.setFullName(resultSet.getString("fullname"));
                user1.setPassword(resultSet.getString("password"));
                user1.setAvatar(resultSet.getString("avatar"));
                user1.setSex(resultSet.getBoolean("sex"));
                user1.setRole_id(resultSet.getInt("role_id"));
                user1.setStatus(resultSet.getBoolean("status"));
                user1.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return user1;
    }

    @Override
    public boolean findByUserName(String userName) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("{call sreach_user_name(?)}");
            callSt.setString(1, userName);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);

        }
        return true;
    }

    @Override
    public List<UserViewAdminRes> findByFullNameOrUserName(String text) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        List<UserViewAdminRes> userList = new ArrayList<>();
        try {
            callSt = conn.prepareCall("{call pro_find_search_user(?)}");
            callSt.setString(1,text);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                UserViewAdminRes u = new UserViewAdminRes();
                u.setUserId(rs.getInt("user_id"));
                u.setUserName(rs.getString("user_name"));
                u.setEmail(rs.getString("email"));
                u.setFullName(rs.getString("fullname"));
                u.setAvatar(rs.getString("avatar"));
                u.setSex(rs.getBoolean("sex"));
                u.setStatus(rs.getBoolean("status"));
                u.setAge(rs.getInt("age"));
                userList.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return userList;
    }
}
