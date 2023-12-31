package ra.webwalefashion.model.DAO.impl;

import org.springframework.stereotype.Repository;
import ra.webwalefashion.model.DAO.BranDAO;
import ra.webwalefashion.model.entity.Brand;
import ra.webwalefashion.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class BranDAOImpl implements BranDAO {

    @Override
    public List<Brand> findALl() {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        List<Brand> brandList = new ArrayList<>();
        try {
            callSt = conn.prepareCall("{call pro_select_brand()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setBrandId(rs.getInt("brand_id"));
                brand.setNameBrand(rs.getString("brand_name"));
                brandList.add(brand);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return brandList;
    }

    @Override
    public Brand findById(Integer integer) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        Brand brand = null;
        try {
            callSt = conn.prepareCall("{call pro_find_by_id_brand(?)}");
            callSt.setInt(1,integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                brand = new Brand();
                brand.setBrandId(rs.getInt("brand_id"));
                brand.setNameBrand(rs.getString("brand_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return brand;
    }

    @Override
    public void save(Brand brand) {

    }

    @Override
    public void delete(Integer integer) {

    }
}
