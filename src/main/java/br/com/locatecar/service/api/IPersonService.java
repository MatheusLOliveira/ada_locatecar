package br.com.locatecar.service.api;

import br.com.locatecar.model.Person;

public interface IPersonService extends IService<Person> {

    Person getOneByPrimaryKey(String document);

}
