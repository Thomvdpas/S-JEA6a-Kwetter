package main.dao;

import java.util.List;

/**
 * @author Thom van de Pas on 1-3-2018
 */
public interface GenericDao<T> {

    /**
     * Creates a entity.
     *
     * @param t
     * @returns the created entity.
     */
    T create(T t);

    /**
     * Updates an entity.
     *
     * @param t
     * @returns the updated entity.
     */
    T update(T t);

    /**
     * Deletes a entity.
     *
     * @param t
     */
    void delete(T t);

    /**
     * Deletes an entity by its id.
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * Finds a entity by its id.
     *
     * @param id
     * @returns the found entity.
     */
    T findById(Long id);

    /**
     * Finds all the entities of a type.
     *
     * @returns List of entities.
     */
    List<T> findAll();
}
