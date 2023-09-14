package br.com.locatecar.service.api;

import java.util.List;

public interface IService<T> {

    void add(T obj);
    T update(T obj);
    T delete(T obj);
    List<T> getAll();

}
