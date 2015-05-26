package org.fscraper.data.sql.bo;

import org.fscraper.data.sql.dao.CoreDAO;
import org.apache.log4j.Logger;
import org.fscraper.data.sql.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jr on 3/21/2015.
 */

@Service("CoreBO")
public class CoreBOImpl implements CoreBO {

    private static Logger logger = Logger.getLogger(CoreBOImpl.class.getName());

    @Autowired
    @Qualifier("CoreDAO")
    private CoreDAO coreDAO;

    public CoreBOImpl() {

    }

    @Override
    public Brand addBrand(String name) {

        Brand brand = new Brand();

        brand.setName(name);

        brand = (Brand)save(brand);

        return brand;
    }

    @Override
    public Color addColor(String colorValue) {

        Color color = new Color();

        color.setName(colorValue);

        return (Color)save(color);
    }

    @Override
    public ProductImage addProductImage(Product product, String imgURL) {

        ProductImage productImage = new ProductImage();

        productImage.setProduct(product);
        productImage.setUrl(imgURL);

        Integer productImageID = (Integer)coreDAO.save(productImage);

        productImage.setId(productImageID);

        return productImage;

    }

    @Override
    public IEntity save(IEntity object) {

        Integer id = (Integer)coreDAO.save(object);
        object.setId(id);
        return object;
    }

    @Override
    public void saveOrUpdate(IEntity object) {
        coreDAO.saveOrUpdate(object);
    }

    @Override
    public <T extends IEntity> List<T> find(Class<T> entityType, String attr, Object value) {
        return coreDAO.find(entityType, attr, value);
    }

    @Override
    public <T extends IEntity> T findWithLike(Class<T> entityType, String attr, Object value) {
        return coreDAO.findWithLike(entityType, attr, value);
    }

    @Override
    public <T extends IEntity> List<T> find(Class<T> entityType,
                                                Map<String, Object> params,
                                                Boolean multi) {
        return coreDAO.find(entityType, params, multi);
    }

    @Override
    public Size addSize(Product product, String value) {

        Size size = new Size();

        size.setCategory(product.getCategory());
        size.setStore(product.getStore());
        size.setValue(value);

        return (Size)save(size);
    }
}
