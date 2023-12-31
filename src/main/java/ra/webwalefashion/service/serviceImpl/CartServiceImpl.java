package ra.webwalefashion.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webwalefashion.model.DAO.CartDAO;
import ra.webwalefashion.model.DAO.CartItemDAO;
import ra.webwalefashion.model.DAO.ProductDAO;
import ra.webwalefashion.model.entity.Cart;
import ra.webwalefashion.model.entity.CartItem;
import ra.webwalefashion.model.entity.Product;
import ra.webwalefashion.service.CartService;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartItemDAO cartItemDAO;
    @Autowired
    private CartDAO cartDAO;
    @Autowired
    private ProductDAO productDAO;

    @Override
    public void addToCart(Integer productId, Integer userId) {
        Cart cart = cartDAO.findUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cartDAO.save(cart);
        }
        CartItem cartItem = cartItemDAO.findByProductIdAndCartId(productId, cart.getId());
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            Product p = productDAO.findById(cartItem.getProductId());
            cartItem.setPrice(p.getPrice() * cartItem.getQuantity());
            cartItemDAO.save(cartItem);
            cartDAO.updateCart(cart.getId());
            productDAO.updateProduct(cartItem.getProductId());
        } else {
            CartItem newItem = new CartItem();
            newItem.setCartId(cart.getId());
            newItem.setProductId(productId);
            newItem.setQuantity(1);
            newItem.setPrice(productDAO.findById(productId).getPrice() * newItem.getQuantity());
            cartItemDAO.save(newItem);
            cartDAO.updateCart(cart.getId());
            productDAO.updateProduct(newItem.getProductId());
        }
    }

    @Override
    public void delete(Integer id) {
        cartDAO.delete(id);
    }

    @Override
    public Cart findByUser(Integer userId) {
        return cartDAO.findUserId(userId);
    }

    @Override
    public Cart findById(Integer id) {
        return cartDAO.findById(id);
    }

    @Override
    public void checkCartUser(Integer userId) {
        if(cartDAO.findUserId(userId) == null){
            Cart cart = new Cart();
            cart.setUserId(userId);
            cartDAO.save(cart);
        }
    }

}
