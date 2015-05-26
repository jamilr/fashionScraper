package org.fscraper.data.sql.bo;

import org.fscraper.data.sql.model.*;

import java.util.List;
import java.util.Map;

/**
 * Created by jr on 3/21/2015.
 */

public interface CoreBO {

    Brand           addBrand(String name);
    Color addColor(String color);
    ProductImage addProductImage(Product product, String imgURL);

    IEntity save(IEntity object);
    void            saveOrUpdate(IEntity object);

    <T extends IEntity> List<T> find(Class<T> entityType, String attr, Object value);

    <T extends IEntity> T findWithLike(Class<T> entityType, String attr, Object value);

    <T extends IEntity> List<T> find(Class<T> entityType, Map<String, Object> params, Boolean multi);

    Size addSize(Product product, String value);
}
