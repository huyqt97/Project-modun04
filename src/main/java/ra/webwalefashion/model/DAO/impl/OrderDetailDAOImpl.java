package ra.webwalefashion.model.DAO.impl;

import org.springframework.stereotype.Repository;
import ra.webwalefashion.model.DAO.OrderDetailDAO;
import ra.webwalefashion.model.entity.OrderDetail;
import ra.webwalefashion.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public List<OrderDetail> findALl() {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            callSt = conn.prepareCall("{call pro_find_all_order_detail()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(rs.getInt("orders_detail_id"));
                orderDetail.setOrderId(rs.getInt("order_id"));
                orderDetail.setProductName(rs.getString("product_name"));
                orderDetail.setProductSize(rs.getString("product_size"));
                orderDetail.setProductCategory(rs.getString("product_category"));
                orderDetail.setProductBrand(rs.getString("product_brand"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setPrice(rs.getDouble("price"));
                orderDetailList.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return orderDetailList;
    }

    @Override
    public OrderDetail findById(Integer integer) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        OrderDetail orderDetail = null;
        try {
            callSt = conn.prepareCall("{call pro_find_by_id_order_detail(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                orderDetail = new OrderDetail();
                orderDetail.setId(rs.getInt("orders_detail_id"));
                orderDetail.setOrderId(rs.getInt("order_id"));
                orderDetail.setProductName(rs.getString("product_name"));
                orderDetail.setProductSize(rs.getString("product_size"));
                orderDetail.setProductCategory(rs.getString("product_category"));
                orderDetail.setProductBrand(rs.getString("product_brand"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setPrice(rs.getDouble("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return orderDetail;
    }

    @Override
    public void save(OrderDetail orderDetail) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        try {
            if (findById(orderDetail.getId()) != null) {
                callSt = conn.prepareCall("{call pro_update_order_detail(?,?,?,?,?,?,?,?)}");
                callSt.setInt(1, orderDetail.getId());
                callSt.setInt(2, orderDetail.getOrderId());
                callSt.setString(3, orderDetail.getProductName());
                callSt.setString(4, orderDetail.getProductSize());
                callSt.setString(5, orderDetail.getProductCategory());
                callSt.setString(6, orderDetail.getProductBrand());
                callSt.setInt(7, orderDetail.getQuantity());
                callSt.setDouble(8, orderDetail.getPrice());
                callSt.executeUpdate();
            } else {
                callSt = conn.prepareCall("{call pro_create_order_detail(?,?,?,?,?,?,?)}");
                callSt.setInt(1, orderDetail.getOrderId());
                callSt.setString(2, orderDetail.getProductName());
                callSt.setString(3, orderDetail.getProductSize());
                callSt.setString(4, orderDetail.getProductCategory());
                callSt.setString(5, orderDetail.getProductBrand());
                callSt.setInt(6, orderDetail.getQuantity());
                callSt.setDouble(7, orderDetail.getPrice());
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
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("{call pro_delete_order_detail(?)}");
            callSt.setInt(1, integer);
            callSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public List<OrderDetail> findAllToOrder(Integer id) {
        Connection conn =ConnectDB.getConnection();
        CallableStatement callSt = null;
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            callSt = conn.prepareCall("{call pro_order_detail_find_by_orderId(?)}");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(rs.getInt("orders_detail_id"));
                orderDetail.setOrderId(rs.getInt("order_id"));
                orderDetail.setProductName(rs.getString("product_name"));
                orderDetail.setProductSize(rs.getString("product_size"));
                orderDetail.setProductCategory(rs.getString("product_category"));
                orderDetail.setProductBrand(rs.getString("product_brand"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setPrice(rs.getDouble("price"));
                orderDetailList.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetailList;

    }
}
