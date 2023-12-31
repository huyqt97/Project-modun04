package ra.webwalefashion.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webwalefashion.model.DAO.OrderDetailDAO;
import ra.webwalefashion.model.entity.OrderDetail;
import ra.webwalefashion.service.OrderDetailService;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Override
    public List<OrderDetail> findAllToOrder(Integer id) {
        return orderDetailDAO.findAllToOrder(id);
    }
}
