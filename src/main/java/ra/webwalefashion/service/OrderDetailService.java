package ra.webwalefashion.service;

import ra.webwalefashion.model.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> findAllToOrder(Integer id);
}
