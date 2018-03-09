package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Thom van de Pas on 1-3-2018
 */
public interface GenericDao<T> {

    T create(T t);

    T update(T t);

    void delete(T t);

    void deleteById(Long id);

    T findById(Long id);

    List<T> findAll();
}
