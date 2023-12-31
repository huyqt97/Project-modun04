package ra.webwalefashion.service.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webwalefashion.DTO.request.OrderReq;
import ra.webwalefashion.model.DAO.*;
import ra.webwalefashion.model.entity.CartItem;
import ra.webwalefashion.model.entity.Order;
import ra.webwalefashion.model.entity.OrderDetail;
import ra.webwalefashion.model.entity.Product;
import ra.webwalefashion.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Autowired
    private CartItemDAO cartItemDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private SizeDAO sizeDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private BranDAO branDAO;
    @Autowired
    private CartDAO cartDAO;

    @Override
    public void create(OrderReq orderReq, Integer cartId) {
        ModelMapper modelMapper = new ModelMapper();
        orderDAO.save(modelMapper.map(orderReq, Order.class));
        for (CartItem c : cartItemDAO.findALl()) {
            if (c.getCartId() == cartId) {
                OrderDetail orderDetail = new OrderDetail();
                Product p = productDAO.findById(c.getProductId());
                orderDetail.setOrderId(orderIdMax());
                orderDetail.setProductName(p.getName());
                orderDetail.setProductSize(sizeDAO.findById(p.getSizeId()).getSizeName());
                orderDetail.setProductCategory(categoryDAO.findById(p.getCategoryId()).getCategoryName());
                orderDetail.setProductBrand(branDAO.findById(p.getBrand()).getNameBrand());
                orderDetail.setQuantity(c.getQuantity());
                orderDetail.setPrice(c.getPrice());
                orderDetailDAO.save(orderDetail);
                orderDAO.updateOrder(orderDetail.getOrderId());
                cartItemDAO.delete(c.getId());
                cartDAO.updateCart(c.getCartId());
                productDAO.updateProduct(c.getProductId());
            }
        }
    }

    public Integer orderIdMax() {
        int max = 0;
        for (Order o : orderDAO.findALl()) {
            if (o.getOrder_id() > max) {
                max = o.getOrder_id();
            }
        }
        return max;
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findALl();
    }

    @Override
    public List<Order> findAllPending() {
        List<Order> orderList = new ArrayList<>();
        for (Order o : orderDAO.findALl()) {
            if (!o.isStatus()) {
                orderList.add(o);
            }
        }
        return orderList;
    }

    @Override
    public List<Order> findAllHistory() {
        List<Order> orderList = new ArrayList<>();
        for (Order o : orderDAO.findALl()) {
            if (o.isStatus()) {
                orderList.add(o);
            }
        }
        return orderList;
    }

    @Override
    public void upStatus(Integer id) {
        Order order = orderDAO.findById(id);
        order.setStatus(true);
        orderDAO.save(order);
    }

    @Override
    public List<Order> allToUserLogin(Integer id) {
        List<Order> orderList = new ArrayList<>();
        for (Order o : findAll()) {
            if (o.getUser_id() == id && !o.isStatus()) {
                orderList.add(o);
            }
        }
        return orderList;
    }

    @Override
    public List<Order> allToUserLoginHistory(Integer id) {
        List<Order> orderList = new ArrayList<>();
        for (Order o : findAll()) {
            if (o.getUser_id() == id && o.isStatus()) {
                orderList.add(o);
            }
        }
        return orderList;
    }

    @Override
    public void delete(Integer id) {
        orderDAO.delete(id);
    }

}
