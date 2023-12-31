package ra.webwalefashion.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.webwalefashion.model.DAO.SizeDAO;
import ra.webwalefashion.model.DAO.impl.SizeDAOImpl;
import ra.webwalefashion.model.entity.Size;
import ra.webwalefashion.service.SizeService;

import java.util.List;
@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeDAO sizeDAO;
    @Override
    public List<Size> findAll() {
        return sizeDAO.findALl();
    }
}
