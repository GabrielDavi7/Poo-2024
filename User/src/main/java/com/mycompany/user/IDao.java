package com.mycompany.user;

import java.util.List;

public interface IDao<E> {
    Long save(E entity);
    void update(E entity);
    E findById(Long id);
    List<E> findAll();
    void delete(Long id);
}
