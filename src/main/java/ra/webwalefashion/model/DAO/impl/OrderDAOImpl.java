package ra.webwalefashion.model.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.webwalefashion.model.DAO.OrderDAO;
import ra.webwalefashion.model.DAO.OrderDetailDAO;
import ra.webwalefashion.model.entity.Order;
import ra.webwalefashion.model.entity.OrderDetail;
import ra.webwalefashion.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class OrderDAOImpl implements OrderDAO {
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Override
    public List<Order> findALl() {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        List<Order> orderList = new ArrayList<>();
        try {
            callSt = conn.prepareCall("{call pro_find_all_order()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrder_id(rs.getInt("order_id"));
                o.setUser_id(rs.getInt("user_id"));
                o.setDate(rs.getDate("date"));
                o.setPhone(rs.getString("phone"));
                o.setAddress(rs.getString("address"));
                o.setStatus(rs.getBoolean("status"));
                o.setNameUser(rs.getString("name_user"));
                o.setQuantity(rs.getInt("total_quantity"));
                o.setPrice(rs.getDouble("total_price"));
                orderList.add(o);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return orderList;
    }

    @Override
    public Order findById(Integer integer) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        Order order = null;
        try {
            callSt = conn.prepareCall("{call pro_find_by_id_order(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setUser_id(rs.getInt("user_id"));
                order.setDate(rs.getDate("date"));
                order.setPhone(rs.getString("phone"));
                order.setAddress(rs.getString("address"));
                order.setStatus(rs.getBoolean("status"));
                order.setNameUser(rs.getString("name_user"));
                order.setQuantity(rs.getInt("total_quantity"));
                order.setPrice(rs.getDouble("total_price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return order;
    }

    @Override
    public void save(Order order) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        try {
            if (findById(order.getOrder_id()) != null) {
                callSt = conn.prepareCall("{call pro_update_order(?,?,?,?,?,?,?,?)}");
                callSt.setInt(1,order.getOrder_id());
                callSt.setInt(2,order.getUser_id());
                callSt.setString(3,order.getPhone());
                callSt.setString(4,order.getAddress());
                callSt.setString(5,order.getNameUser());
                callSt.setInt(6,order.getQuantity());
                callSt.setDouble(7,order.getPrice());
                callSt.setBoolean(8,order.isStatus());
                callSt.executeUpdate();
            }else {
                callSt = conn.prepareCall("{call pro_create_order(?,?,?,?)}");
                callSt.setInt(1,order.getUser_id());
                callSt.setString(2,order.getPhone());
                callSt.setString(3,order.getAddress());
                callSt.setString(4,order.getNameUser());
                callSt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void delete(Integer integer) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("{call pro_delete_order(?)}");
            callSt.setInt(1,integer);
            callSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public Order searchUserId(Integer userId) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        Order order = null;
        try {
            callSt = conn.prepareCall("{call pro_search_order_find_By_userId(?)}");
            callSt.setInt(1,userId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                order = new Order();
                order.setOrder_id(rs.getInt("order_id"));
                order.setUser_id(rs.getInt("user_id"));
                order.setDate(rs.getDate("date"));
                order.setPhone(rs.getString("phone"));
                order.setAddress(rs.getString("address"));
                order.setStatus(rs.getBoolean("status"));
                order.setNameUser(rs.getString("name_user"));
                order.setQuantity(rs.getInt("total_quantity"));
                order.setPrice(rs.getDouble("total_price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    @Override
    public void updateOrder(Integer orderId) {
        Order order = findById(orderId);
        double sumPrice = 0;
        int sumQuantity = 0;
        for (OrderDetail o : orderDetailDAO.findALl()) {
            if (o.getOrderId() == orderId) {
                sumPrice = sumPrice + o.getPrice();
                sumQuantity = sumQuantity + o.getQuantity();
            }
        }
        order.setQuantity(sumQuantity);
        order.setPrice(sumPrice);
        save(order);
    }
}
