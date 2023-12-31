package ra.webwalefashion.service;

import ra.webwalefashion.DTO.request.OrderReq;
import ra.webwalefashion.model.entity.Order;

import java.util.List;

public interface OrderService {
    void create(OrderReq orderReq,Integer cartId);
    List<Order> findAll();
    List<Order> findAllPending();
    List<Order> findAllHistory();
    void upStatus(Integer id);
    List<Order> allToUserLogin(Integer id);
    List<Order> allToUserLoginHistory(Integer id);
    void delete(Integer id);
}
