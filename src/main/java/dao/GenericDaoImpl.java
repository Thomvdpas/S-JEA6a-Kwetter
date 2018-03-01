package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Thom van de Pas on 1-3-2018
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> type;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    public T create(T t) {
        this.entityManager.persist(t);
        return t;
    }

    public void delete(Object object) {
        this.entityManager.remove(this.entityManager.merge(object));
    }

    public T findById(Object object) {
        return (T) this.entityManager.find(type, object);
    }

    public T update(T t) {
        return this.entityManager.merge(t);
    }
}
