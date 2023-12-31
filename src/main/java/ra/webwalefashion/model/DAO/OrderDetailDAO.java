package ra.webwalefashion.model.DAO;

import ra.webwalefashion.model.entity.OrderDetail;

import java.util.List;

public interface OrderDetailDAO extends IGenericDAO<OrderDetail,Integer>{
    List<OrderDetail> findAllToOrder(Integer id);
}
