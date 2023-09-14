package br.com.locatecar.repository.api;

import java.util.List;

public interface Repository<T> {

    void add(T obj);
    T update(T obj);
    T delete(T obj);
    List<T> getAll();

}
