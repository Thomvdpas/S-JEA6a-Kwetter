package dao;

import java.util.List;

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
