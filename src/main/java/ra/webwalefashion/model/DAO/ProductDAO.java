package ra.webwalefashion.model.DAO;

import ra.webwalefashion.DTO.response.ProductViewAdminRes;
import ra.webwalefashion.DTO.response.ProductViewShopRes;
import ra.webwalefashion.model.entity.Product;

import java.util.List;

public interface ProductDAO extends IGenericDAO<Product,Integer>{
    List<ProductViewAdminRes> findAllViewAdmin();
    ProductViewShopRes findByIdViewShop(Integer id);
    List<ProductViewAdminRes> findByNameProduct(String text);
    List<ProductViewAdminRes> findAllViewUserCategory(Integer id);
    List<ProductViewAdminRes> findAllViewUserBrand(Integer id);
    List<ProductViewAdminRes> findAllViewUserSize(Integer id);
    List<ProductViewAdminRes> findAllViewUserPrice(Integer min, Integer max);
    List<ProductViewAdminRes> searchByName(String search);
    void updateProduct(Integer productId);
}
