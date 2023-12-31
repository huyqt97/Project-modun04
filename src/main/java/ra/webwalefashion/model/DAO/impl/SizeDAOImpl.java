package ra.webwalefashion.model.DAO.impl;

import org.springframework.stereotype.Repository;
import ra.webwalefashion.model.DAO.SizeDAO;
import ra.webwalefashion.model.entity.Size;
import ra.webwalefashion.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class SizeDAOImpl implements SizeDAO {
    @Override
    public List<Size> findALl() {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callst = null;
        List<Size> sizeList = new ArrayList<>();
        try {
            callst = conn.prepareCall("{call pro_select_size()}");
            ResultSet rs = callst.executeQuery();
            while (rs.next()){
                Size size = new Size();
                size.setSizeId(rs.getInt("size_id"));
                size.setSizeName(rs.getString("size_name").toUpperCase());
                sizeList.add(size);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
            System.out.println(sizeList);
        }
        return sizeList;
    }

    @Override
    public Size findById(Integer integer) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;
        Size size = null;
        try {
            callSt = conn.prepareCall("{call pro_find_by_id_size(?)}");
            callSt.setInt(1,integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                size = new Size();
                size.setSizeId(rs.getInt("size_id"));
                size.setSizeName(rs.getString("size_name").toUpperCase());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return size;
    }

    @Override
    public void save(Size size) {

    }

    @Override
    public void delete(Integer integer) {

    }
}
