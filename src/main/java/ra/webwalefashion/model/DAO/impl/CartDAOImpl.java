package ra.webwalefashion.model.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.webwalefashion.model.DAO.CartDAO;
import ra.webwalefashion.model.DAO.CartItemDAO;
import ra.webwalefashion.model.entity.Cart;
import ra.webwalefashion.model.entity.CartItem;
import ra.webwalefashion.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO {
    @Autowired
    private CartItemDAO cartItemDAO;

    @Override
    public List<Cart> findALl() {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        List<Cart> cartList = new ArrayList<>();
        try {
            callSt = conn.prepareCall("{call pro_find_all_cart()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Cart c = new Cart();
                c.setId(rs.getInt("cart_id"));
                c.setUserId(rs.getInt("user_id"));
                c.setTotalQuantity(rs.getInt("total_quantity"));
                c.setTotalPrice(rs.getDouble("total_price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return cartList;
    }

    @Override
    public Cart findById(Integer integer) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        Cart c = null;
        try {
            callSt = conn.prepareCall("{call pro_find_by_id_cart(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                c = new Cart();
                c.setId(rs.getInt("cart_id"));
                c.setUserId(rs.getInt("user_id"));
                c.setTotalQuantity(rs.getInt("total_quantity"));
                c.setTotalPrice(rs.getDouble("total_price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return c;
    }

    @Override
    public void save(Cart cart) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        try {
            if (findById(cart.getId()) != null) {
                callSt = conn.prepareCall("{call pro_update_cart(?,?,?,?)}");
                callSt.setInt(1, cart.getId());
                callSt.setInt(2, cart.getUserId());
                callSt.setInt(3, cart.getTotalQuantity());
                callSt.setDouble(4, cart.getTotalPrice());
                callSt.executeUpdate();
            } else {
                callSt = conn.prepareCall("{call pro_create_cart(?)}");
                callSt.setInt(1, cart.getUserId());
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
            callSt = conn.prepareCall("{call pro_delete_cart(?)}");
            callSt.setInt(1, integer);
            callSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public Cart findUserId(Integer userId) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        Cart c = null;
        try {
            callSt = conn.prepareCall("{call pro_find_by_userid_cart(?)}");
            callSt.setInt(1, userId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                c = new Cart();
                c.setId(rs.getInt("cart_id"));
                c.setUserId(rs.getInt("user_id"));
                c.setTotalQuantity(rs.getInt("total_quantity"));
                c.setTotalPrice(rs.getDouble("total_price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return c;
    }

    @Override
    public void updateCart(Integer cartId) {
        Cart cart = findById(cartId);
        double sumPrice = 0;
        int sumQuantity = 0;
        for (CartItem c : cartItemDAO.findALl()) {
            if (c.getCartId() == cartId) {
                sumPrice = sumPrice + c.getPrice();
                sumQuantity = sumQuantity + c.getQuantity();
            }
        }
        cart.setTotalQuantity(sumQuantity);
        cart.setTotalPrice(sumPrice);
        save(cart);
    }
}
