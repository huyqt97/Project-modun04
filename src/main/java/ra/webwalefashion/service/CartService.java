package ra.webwalefashion.service;

import ra.webwalefashion.model.entity.Cart;

public interface CartService {
    void addToCart(Integer productId,Integer userId);
    void delete(Integer id);
    Cart findByUser(Integer userId);
    Cart findById(Integer id);
     void checkCartUser(Integer userId);
}
