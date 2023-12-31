package ra.webwalefashion.model.DAO;

import ra.webwalefashion.model.entity.CartItem;

public interface CartItemDAO extends IGenericDAO<CartItem,Integer>{
    CartItem findByProductIdAndCartId(Integer productId,Integer cartId);
}
