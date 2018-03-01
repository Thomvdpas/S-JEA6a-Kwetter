package dao;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Thom van de Pas on 1-3-2018
 */
public interface GenericDao<T> {

    T create(T t);

    void delete(Object id);

    T findById(Object id);

    T update(T t);
}
