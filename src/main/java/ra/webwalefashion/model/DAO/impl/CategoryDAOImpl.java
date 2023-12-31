package ra.webwalefashion.model.DAO.impl;

import org.springframework.stereotype.Repository;
import ra.webwalefashion.model.DAO.CategoryDAO;
import ra.webwalefashion.model.entity.Brand;
import ra.webwalefashion.model.entity.Category;
import ra.webwalefashion.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public List<Category> findALl() {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callst = null;
        List<Category> categoryList = new ArrayList<>();
        try {
            callst = conn.prepareCall("{call pro_select_category()}");
            ResultSet rs = callst.executeQuery();
            while (rs.next()){
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return categoryList;
    }

    @Override
    public Category findById(Integer integer) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        Category category = null;
        try {
            callSt = conn.prepareCall("{call pro_find_by_id_category(?)}");
            callSt.setInt(1,integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void delete(Integer integer) {

    }
}
