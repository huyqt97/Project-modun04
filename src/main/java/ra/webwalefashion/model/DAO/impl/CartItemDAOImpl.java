package ra.webwalefashion.model.DAO.impl;

import org.springframework.stereotype.Repository;
import ra.webwalefashion.model.DAO.CartItemDAO;
import ra.webwalefashion.model.entity.CartItem;
import ra.webwalefashion.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartItemDAOImpl implements CartItemDAO {
    @Override
    public List<CartItem> findALl() {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        List<CartItem> cartItemList = new ArrayList<>();
        try {
            callSt = conn.prepareCall("{call pro_find_all_cartitem()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                CartItem c = new CartItem();
                c.setId(rs.getInt("cartItem_id"));
                c.setCartId(rs.getInt("cart_id"));
                c.setProductId(rs.getInt("product_id"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getDouble("price"));
                cartItemList.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return cartItemList;
    }

    @Override
    public CartItem findById(Integer integer) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        CartItem c = null;
        try {
            callSt = conn.prepareCall("{call pro_find_by_id_cartitem(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                c = new CartItem();
                c.setId(rs.getInt("cartItem_id"));
                c.setCartId(rs.getInt("cart_id"));
                c.setProductId(rs.getInt("product_id"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getDouble("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return c;
    }

    @Override
    public void save(CartItem cartItem) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        try {
            if (findById(cartItem.getId()) != null) {
                callSt = conn.prepareCall("{call pro_update_cartitem(?,?,?,?,?)}");
                callSt.setInt(1, cartItem.getId());
                callSt.setInt(2, cartItem.getCartId());
                callSt.setInt(3, cartItem.getProductId());
                callSt.setInt(4, cartItem.getQuantity());
                callSt.setDouble(5, cartItem.getPrice());
                callSt.executeUpdate();
            } else {
                callSt = conn.prepareCall("{call pro_create_cartitem(?,?,?,?)}");
                callSt.setInt(1, cartItem.getCartId());
                callSt.setInt(2, cartItem.getProductId());
                callSt.setInt(3, cartItem.getQuantity());
                callSt.setDouble(4, cartItem.getPrice());
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
            callSt = conn.prepareCall("{call pro_delete_cartitem(?)}");
            callSt.setInt(1, integer);
            callSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public CartItem findByProductIdAndCartId(Integer productId, Integer cartId) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        CartItem c = null;
        try {
            callSt = conn.prepareCall("{call pro_search_cartitem_find_by_product_cart(?,?)}");
            callSt.setInt(1, productId);
            callSt.setInt(2, cartId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                c = new CartItem();
                c.setId(rs.getInt("cartItem_id"));
                c.setCartId(rs.getInt("cart_id"));
                c.setProductId(rs.getInt("product_id"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getDouble("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return c;
    }
}
