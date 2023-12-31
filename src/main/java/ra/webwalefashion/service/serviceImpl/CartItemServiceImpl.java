package ra.webwalefashion.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webwalefashion.DTO.response.CartItemViewUserRes;
import ra.webwalefashion.model.DAO.CartDAO;
import ra.webwalefashion.model.DAO.CartItemDAO;
import ra.webwalefashion.model.DAO.ProductDAO;
import ra.webwalefashion.model.entity.Cart;
import ra.webwalefashion.model.entity.CartItem;
import ra.webwalefashion.model.entity.Product;
import ra.webwalefashion.service.CartItemService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartDAO cartDAO;
    @Autowired
    private CartItemDAO cartItemDAO;
    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<CartItemViewUserRes> findAllToUser(Integer userId) {
        List<CartItemViewUserRes> cartItemViewUserResList = new ArrayList<>();
        Cart cart = cartDAO.findUserId(userId);
        for (CartItem c : cartItemDAO.findALl()) {
            if (cart.getId() == c.getCartId()) {
                Product p = productDAO.findById(c.getProductId());
                CartItemViewUserRes cs = new CartItemViewUserRes();
                cs.setId(c.getId());
                cs.setProductImage(p.getImage());
                cs.setProductName(p.getName());
                cs.setStock(c.getQuantity());
                cs.setPrice(c.getPrice());
                cartItemViewUserResList.add(cs);
            }
        }
        return cartItemViewUserResList;
    }

    @Override
    public void sellSearchToProductAndUser(Integer cartItemId) {
        CartItem cartItem = cartItemDAO.findById(cartItemId);
        if (cartItem.getQuantity() == 1) {
            cartItemDAO.delete(cartItemId);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItem.setPrice(productDAO.findById(cartItem.getProductId()).getPrice() * cartItem.getQuantity());
            cartItemDAO.save(cartItem);
            cartDAO.updateCart(cartItem.getCartId());
        }
    }

    @Override
    public void busSearchToProductAndUser(Integer cartItemId) {
        CartItem cartItem = cartItemDAO.findById(cartItemId);
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        Product p = productDAO.findById(cartItem.getProductId());
        cartItem.setPrice(p.getPrice() * cartItem.getQuantity());
        cartItemDAO.save(cartItem);
        cartDAO.updateCart(cartItem.getCartId());
    }

    @Override
    public void delete(Integer id) {
        CartItem cartItem =  cartItemDAO.findById(id);
        cartItemDAO.delete(id);
        cartDAO.updateCart(cartItem.getCartId());
    }
}
