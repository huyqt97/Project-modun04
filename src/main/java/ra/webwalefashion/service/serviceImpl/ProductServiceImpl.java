package ra.webwalefashion.service.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import ra.webwalefashion.DTO.request.ProductEditReq;
import ra.webwalefashion.DTO.response.ProductViewAdminRes;
import ra.webwalefashion.DTO.response.ProductEditRes;
import ra.webwalefashion.DTO.response.ProductUpdateRes;
import ra.webwalefashion.DTO.response.ProductViewShopRes;
import ra.webwalefashion.model.DAO.ProductDAO;
import ra.webwalefashion.model.entity.Product;
import ra.webwalefashion.service.ProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@PropertySource("classpath:upload.properties")
public class ProductServiceImpl implements ProductService {
    @Value("${upload-path}")
    String pathUpload;
    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<ProductViewAdminRes> findAllViewAdmin() {
        return productDAO.findAllViewAdmin();
    }

    @Override
    public void save(ProductUpdateRes productUpdateRes) {
        String fileName = productUpdateRes.getImage().getOriginalFilename();
        File uploadPro = new File(pathUpload);
        if (!uploadPro.exists()) {
            uploadPro.mkdirs();
        }
        try {
            FileCopyUtils.copy(productUpdateRes.getImage().getBytes(), new File(pathUpload + fileName));
            String img = String.valueOf(fileName);
            Product product = new Product();
            product.setName(productUpdateRes.getName());
            product.setImage(img);
            product.setBrand(productUpdateRes.getBrand());
            product.setDes(productUpdateRes.getDes());
            product.setPrice(productUpdateRes.getPrice());
            product.setCategoryId(productUpdateRes.getCategoryId());
            product.setSizeId(productUpdateRes.getSizeId());
            product.setStock(productUpdateRes.getStock());
            productDAO.save(product);
        } catch (IOException ignored) {
            throw new RuntimeException();
        }
    }

    @Override
    public ProductEditRes findById(Integer id) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(productDAO.findById(id), ProductEditRes.class);
    }

    @Override
    public void update(ProductEditReq productEditReq) {
        ModelMapper modelMapper = new ModelMapper();
        Product p = modelMapper.map(productEditReq, Product.class);
        productDAO.save(p);
    }

    @Override
    public ProductViewShopRes findByIdViewShop(Integer ids) {
        return productDAO.findByIdViewShop(ids);
    }

    @Override
    public List<ProductViewAdminRes> findByNameProduct(String text) {
        return productDAO.findByNameProduct(text);
    }

    @Override
    public List<ProductViewAdminRes> findAllViewUserCategory(Integer id) {
        return productDAO.findAllViewUserCategory(id);
    }

    @Override
    public List<ProductViewAdminRes> findAllViewUserBrand(Integer id) {
        return productDAO.findAllViewUserBrand(id);
    }

    @Override
    public List<ProductViewAdminRes> findAllViewUserSize(Integer id) {
        return productDAO.findAllViewUserSize(id);
    }

    @Override
    public List<ProductViewAdminRes> findAllViewUserPrice(Integer min, Integer max) {
        return productDAO.findAllViewUserPrice(min,max);
    }

    @Override
    public List<ProductViewAdminRes> searchByName(String search) {
        return productDAO.searchByName(search);
    }
}
