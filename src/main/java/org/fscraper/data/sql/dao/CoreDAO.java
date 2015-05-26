package org.fscraper.data.sql.dao;

import org.fscraper.data.sql.model.IEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by jr on 3/21/2015.
 */

public interface CoreDAO {

    Serializable save(IEntity object);

    void saveOrUpdate(IEntity object);

    <T extends IEntity> List<T> find(Class<T> entityType, String attr, Object value);

    <T extends IEntity> T findWithLike(Class<T> entityType, String attr, Object value);

    <T extends IEntity> List<T> find(Class<T> entityType,
                                            Map<String, Object> params,
                                            Boolean multi);
}
