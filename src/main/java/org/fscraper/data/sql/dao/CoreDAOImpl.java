package org.fscraper.data.sql.dao;

import org.fscraper.data.sql.model.IEntity;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jr on 3/21/2015.
 */

@Repository("CoreDAO")
@Transactional
public class CoreDAOImpl implements CoreDAO {

    private static Logger logger = Logger.getLogger(CoreDAOImpl.class.getName());

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public CoreDAOImpl() {

    }

    @Override
    public Serializable save(IEntity object) {

        Integer id = (Integer)sessionFactory.getCurrentSession().save(object);
        object.setId(id);
        return id;
    }

    @Override
    public void saveOrUpdate(IEntity object) {
        sessionFactory.getCurrentSession().saveOrUpdate(object);
    }

    @Override
    public <T extends IEntity> List<T> find(Class<T> entityType, String attr, Object value) {

        List<T> entity = (List<T>)sessionFactory.getCurrentSession().createCriteria(entityType)
                .add(Restrictions.eq(attr, value)).list();

        return entity;
    }

    @Override
    public <T extends IEntity> T findWithLike(Class<T> entityType, String attr, Object value) {

        T entity = (T)sessionFactory.getCurrentSession().createCriteria(entityType)
                .add(Restrictions.ilike(attr, value)).uniqueResult();

        return entity;
    }

    @Override
    public <T extends IEntity> List<T> find(Class<T> entityType,
                                            Map<String, Object> params,
                                            Boolean multi) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityType);

        List<Criterion> eqList = new ArrayList<>();

        Criterion c;
        for (String key: params.keySet()){

            Object value = params.get(key);

            if (value instanceof List) {
                List valueList = (List)value;
                c = Restrictions.in(key, valueList);
            } else
                c = Restrictions.eq(key, params.get(key));

            eqList.add(c);
        }

        criteria.add(Restrictions.and(eqList.toArray(new Criterion[0])));

        List<T> results = new ArrayList<>();

        if (multi)
            results = (List<T>)criteria.list();
        else {

            T obj = (T)criteria.uniqueResult();

            if (obj != null)
                results.add(obj);
        }

        return results;
    }
}
