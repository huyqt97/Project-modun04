package ra.webwalefashion.model.DAO;

import ra.webwalefashion.model.entity.Cart;

public interface CartDAO extends IGenericDAO<Cart,Integer> {
    Cart findUserId(Integer userId);
    void updateCart(Integer cartId);
}
