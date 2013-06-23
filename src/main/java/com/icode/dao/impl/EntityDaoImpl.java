package com.icode.dao.impl;

import com.icode.core.model.AbstractEntity;
import com.icode.dao.EntityDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ZhongGang
 * Date: 13-6-23
 * Time: 上午1:32
 */
public class EntityDaoImpl implements EntityDao {

    @PersistenceContext
    protected EntityManager entityManager;


    @Override
    public <T extends AbstractEntity> void saveOrUpdate(T t) {
        entityManager.persist(t);
    }

    @Override
    public <T extends AbstractEntity> T findByGuid(Class<T> clazz, String guid) {
        Query query = entityManager.createQuery("from " + clazz.getName() + " t where t.active = true and t.guid = :guid");
        query.setParameter("guid", guid);
        List resultList = query.getResultList();
        return resultList.isEmpty() ? null : (T) resultList.get(0);
    }
}
