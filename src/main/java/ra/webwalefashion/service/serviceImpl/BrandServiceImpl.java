package ra.webwalefashion.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webwalefashion.model.DAO.BranDAO;
import ra.webwalefashion.model.entity.Brand;
import ra.webwalefashion.service.BrandService;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BranDAO branDAO;
    @Override
    public List<Brand> findAll() {
        return branDAO.findALl();
    }
}
