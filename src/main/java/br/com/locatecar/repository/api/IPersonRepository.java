package br.com.locatecar.repository.api;

import br.com.locatecar.model.Person;

public interface IPersonRepository<T> extends Repository<Person>{

    T getOneByPrimaryKey(String document);

}
