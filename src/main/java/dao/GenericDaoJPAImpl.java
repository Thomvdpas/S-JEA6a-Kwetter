package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Thom van de Pas on 1-3-2018
 */
@Stateless
@JPA
public abstract class GenericDaoJPAImpl<T> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> type;

    public GenericDaoJPAImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    public T create(T t) {
        this.entityManager.persist(t);
        return t;
    }

    public T update(T t) {
        return this.entityManager.merge(t);
    }

    public void delete(Long id) {
        this.entityManager.remove(this.entityManager.merge(id));
    }

    public T findById(Long id) {
        return (T) this.entityManager.find(type, id);
    }

    public List<T> findAll() {
        return entityManager.createNamedQuery("SELECT * FROM " + type.getClass()).getResultList();
    }

    //<editor-fold desc="Getters/Setters">
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    //</editor-fold>
}
