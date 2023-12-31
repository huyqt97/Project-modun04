package ra.webwalefashion.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webwalefashion.model.DAO.CategoryDAO;
import ra.webwalefashion.model.DAO.impl.CategoryDAOImpl;
import ra.webwalefashion.model.entity.Category;
import ra.webwalefashion.service.CategoryService;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public List<Category> findAll() {
       return categoryDAO.findALl();
    }
}
