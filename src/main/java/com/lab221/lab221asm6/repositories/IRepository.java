package com.lab221.lab221asm6.repositories;

import java.util.List;

public interface IRepository<E, K> {

    /**
     * Create a resource.
     * @param entity the entity data
     * @return true if created
     */
    boolean create(E entity);

    /**
     * Find a resource by id.
     * @param id the id of resource
     * @return entity if found
     */
    E findById(K id);

    /**
     * Find all resources.
     * @return list of resources
     */
    List<E> findAll();

    /**
     * Update a resource.
     * @param entity the entity to update
     * @return true if updated
     */
    boolean update(E entity);

    /**
     * Delete a resource by id.
     * @param id the id of resource
     * @return true if deleted
     */
    boolean delete(K id);

    /**
     * Search resources by keyword.
     * @param keyword the keyword
     * @return list of resources
     */
    List<E> search(String keyword);
}
