package dao;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Thom van de Pas on 1-3-2018
 */
public interface GenericDao<T> {

    T create(T t);

    T update(T t);

    void delete(Long id);

    T findById(Long id);
}
