package ra.webwalefashion.service;

import ra.webwalefashion.DTO.request.ProductEditReq;
import ra.webwalefashion.DTO.response.ProductViewAdminRes;
import ra.webwalefashion.DTO.response.ProductEditRes;
import ra.webwalefashion.DTO.response.ProductUpdateRes;
import ra.webwalefashion.DTO.response.ProductViewShopRes;

import java.util.List;

public interface ProductService{
    List<ProductViewAdminRes> findAllViewAdmin();
    void save(ProductUpdateRes productUpdateRes);
    ProductEditRes findById(Integer id);
    void update(ProductEditReq productEditReq);
    ProductViewShopRes findByIdViewShop(Integer ids);
    List<ProductViewAdminRes> findByNameProduct(String text);
    List<ProductViewAdminRes> findAllViewUserCategory(Integer id);
    List<ProductViewAdminRes> findAllViewUserBrand(Integer id);
    List<ProductViewAdminRes> findAllViewUserSize(Integer id);
    List<ProductViewAdminRes> findAllViewUserPrice(Integer min, Integer max);
    List<ProductViewAdminRes> searchByName(String search);
}
