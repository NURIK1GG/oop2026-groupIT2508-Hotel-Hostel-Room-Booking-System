package edu.aitu.oop3.repositories;

import java.util.List;

public interface Repository<T> {
    void save(T entity);
    List<T> findAll();
}
