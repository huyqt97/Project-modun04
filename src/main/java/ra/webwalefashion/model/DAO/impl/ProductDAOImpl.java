package ra.webwalefashion.model.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.webwalefashion.DTO.response.ProductViewAdminRes;
import ra.webwalefashion.DTO.response.ProductViewShopRes;
import ra.webwalefashion.model.DAO.CartItemDAO;
import ra.webwalefashion.model.DAO.ProductDAO;
import ra.webwalefashion.model.entity.CartItem;
import ra.webwalefashion.model.entity.Product;
import ra.webwalefashion.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    @Override
    public List<Product> findALl() {
        return null;
    }

    @Override
    public Product findById(Integer integer) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        Product p = null;
        try {
            callSt = conn.prepareCall("{call pro_product_find_by_id(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name_product"));
                p.setImage(rs.getString("image"));
                p.setBrand(rs.getInt("brand_id"));
                p.setDes(rs.getString("description"));
                p.setPrice(rs.getDouble("unit_price"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setSizeId(rs.getInt("size_id"));
                p.setStock(rs.getInt("stock"));
                p.setSold(rs.getInt("sold"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return p;
    }

    @Override
    public void save(Product product) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        try {
            if (product.getProductId() >= 1) {
                callSt = conn.prepareCall("{call pro_update_product(?,?,?,?,?,?,?,?,?)}");
                callSt.setInt(1, product.getProductId());
                callSt.setString(2, product.getName());
                callSt.setString(3, product.getImage());
                callSt.setInt(4, product.getBrand());
                callSt.setString(5, product.getDes());
                callSt.setDouble(6, product.getPrice());
                callSt.setInt(7, product.getCategoryId());
                callSt.setInt(8, product.getSizeId());
                callSt.setInt(9, product.getStock());
                callSt.executeUpdate();
            } else {
                callSt = conn.prepareCall("{call pro_insert_product(?,?,?,?,?,?,?,?)}");
                callSt.setString(1, product.getName());
                callSt.setString(2, product.getImage());
                callSt.setInt(3, product.getBrand());
                callSt.setString(4, product.getDes());
                callSt.setDouble(5, product.getPrice());
                callSt.setInt(6, product.getCategoryId());
                callSt.setInt(7, product.getSizeId());
                callSt.setInt(8, product.getStock());
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
    public List<ProductViewAdminRes> findAllViewAdmin() {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        List<ProductViewAdminRes> productViewAdminRes = new ArrayList<>();
        try {
            callSt = conn.prepareCall("{call pro_select_product_view_admin()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                ProductViewAdminRes p = new ProductViewAdminRes();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setImage(rs.getString("image"));
                p.setBrand(rs.getString("brand"));
                p.setPrice(rs.getDouble("price"));
                p.setCategoryId(rs.getString("category"));
                p.setSizeId(rs.getString("size").toUpperCase());
                p.setStock(rs.getInt("stock"));
                p.setSold(rs.getInt("sold"));
                productViewAdminRes.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return productViewAdminRes;
    }

    @Override
    public ProductViewShopRes findByIdViewShop(Integer id) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        ProductViewShopRes p = null;
        try {
            callSt = conn.prepareCall("{call pro_find_by_id_product_view_shop(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                p = new ProductViewShopRes();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setImage(rs.getString("image"));
                p.setBrand(rs.getString("brand"));
                p.setDes(rs.getString("des"));
                p.setPrice(rs.getDouble("price"));
                p.setCategory(rs.getString("category"));
                p.setSize(rs.getString("size"));
                p.setStock(rs.getInt("stock"));
                p.setSold(rs.getInt("sold"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return p;
    }

    @Override
    public List<ProductViewAdminRes> findByNameProduct(String text) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        List<ProductViewAdminRes> productViewAdminResList = new ArrayList<>();
        try {
            callSt = conn.prepareCall("{call pro_search_name_product(?)}");
            callSt.setString(1, text);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                ProductViewAdminRes p = new ProductViewAdminRes();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setImage(rs.getString("image"));
                p.setBrand(rs.getString("brand"));
                p.setPrice(rs.getDouble("price"));
                p.setCategoryId(rs.getString("category"));
                p.setSizeId(rs.getString("size").toUpperCase());
                p.setStock(rs.getInt("stock"));
                p.setSold(rs.getInt("sold"));
                productViewAdminResList.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return productViewAdminResList;
    }

    @Override
    public List<ProductViewAdminRes> findAllViewUserCategory(Integer id) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        List<ProductViewAdminRes> productViewAdminResList = new ArrayList<>();
        try {
            callSt = conn.prepareCall("{call pro_search_category_product(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                ProductViewAdminRes p = new ProductViewAdminRes();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setImage(rs.getString("image"));
                p.setBrand(rs.getString("brand"));
                p.setPrice(rs.getDouble("price"));
                p.setCategoryId(rs.getString("category"));
                p.setSizeId(rs.getString("size").toUpperCase());
                p.setStock(rs.getInt("stock"));
                p.setSold(rs.getInt("sold"));
                productViewAdminResList.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return productViewAdminResList;
    }

    @Override
    public List<ProductViewAdminRes> findAllViewUserBrand(Integer id) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        List<ProductViewAdminRes> productViewAdminResList = new ArrayList<>();
        try {
            callSt = conn.prepareCall("{call pro_search_brand_product(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                ProductViewAdminRes p = new ProductViewAdminRes();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setImage(rs.getString("image"));
                p.setBrand(rs.getString("brand"));
                p.setPrice(rs.getDouble("price"));
                p.setCategoryId(rs.getString("category"));
                p.setSizeId(rs.getString("size").toUpperCase());
                p.setStock(rs.getInt("stock"));
                p.setSold(rs.getInt("sold"));
                productViewAdminResList.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return productViewAdminResList;
    }

    @Override
    public List<ProductViewAdminRes> findAllViewUserSize(Integer id) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        List<ProductViewAdminRes> productViewAdminResList = new ArrayList<>();
        try {
            callSt = conn.prepareCall("{call pro_search_size_product(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                ProductViewAdminRes p = new ProductViewAdminRes();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setImage(rs.getString("image"));
                p.setBrand(rs.getString("brand"));
                p.setPrice(rs.getDouble("price"));
                p.setCategoryId(rs.getString("category"));
                p.setSizeId(rs.getString("size").toUpperCase());
                p.setStock(rs.getInt("stock"));
                p.setSold(rs.getInt("sold"));
                productViewAdminResList.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return productViewAdminResList;
    }

    @Override
    public List<ProductViewAdminRes> findAllViewUserPrice(Integer min, Integer max) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        List<ProductViewAdminRes> productViewAdminResList = new ArrayList<>();
        try {
            callSt = conn.prepareCall("{call pro_search_price_product(?,?)}");
            callSt.setInt(1, min);
            callSt.setInt(2, max);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                ProductViewAdminRes p = new ProductViewAdminRes();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setImage(rs.getString("image"));
                p.setBrand(rs.getString("brand"));
                p.setPrice(rs.getDouble("price"));
                p.setCategoryId(rs.getString("category"));
                p.setSizeId(rs.getString("size").toUpperCase());
                p.setStock(rs.getInt("stock"));
                p.setSold(rs.getInt("sold"));
                productViewAdminResList.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return productViewAdminResList;
    }

    @Override
    public List<ProductViewAdminRes> searchByName(String search) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        List<ProductViewAdminRes> productViewAdminResList = new ArrayList<>();
        try {
            callSt = conn.prepareCall("{call pro_search_name_product_view_user(?)}");
            callSt.setString(1, search);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                ProductViewAdminRes p = new ProductViewAdminRes();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setImage(rs.getString("image"));
                p.setBrand(rs.getString("brand"));
                p.setPrice(rs.getDouble("price"));
                p.setCategoryId(rs.getString("category"));
                p.setSizeId(rs.getString("size").toUpperCase());
                p.setStock(rs.getInt("stock"));
                p.setSold(rs.getInt("sold"));
                productViewAdminResList.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productViewAdminResList;
    }

    @Autowired
    CartItemDAO cartItemDAO;

    @Override
    public void updateProduct(Integer productId) {
        Product product = findById(productId);
        int sum = 0;
        for (CartItem c : cartItemDAO.findALl()) {
            if (c.getProductId() == productId) {
                sum = sum + c.getQuantity();
            }
        }
        product.setStock(product.getStock() - sum);
        product.setSold(sum);
    }
}
