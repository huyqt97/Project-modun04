package ra.webwalefashion.service;

import ra.webwalefashion.DTO.response.CartItemViewUserRes;

import java.util.List;

public interface CartItemService {
     List<CartItemViewUserRes> findAllToUser(Integer userId);
     void sellSearchToProductAndUser(Integer cartItemId);
     void busSearchToProductAndUser(Integer cartItemId);
     void delete(Integer id);
}
