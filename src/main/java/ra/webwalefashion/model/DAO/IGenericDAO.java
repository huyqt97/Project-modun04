package ra.webwalefashion.model.DAO;

import java.util.List;

public interface IGenericDAO <OJ,ID>{
    List<OJ> findALl();
    OJ findById(ID id);
    void save(OJ oj);
    void delete(ID id);
}
