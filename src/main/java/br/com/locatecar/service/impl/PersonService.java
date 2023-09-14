package br.com.locatecar.service.impl;

import br.com.locatecar.exceptions.DuplicateException;
import br.com.locatecar.model.Person;
import br.com.locatecar.repository.api.IPersonRepository;
import br.com.locatecar.service.api.IPersonService;

import java.util.List;

public class PersonService implements IPersonService {

    IPersonRepository<Person> personRepository;

    public PersonService(IPersonRepository<Person> personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void add(Person personToAdd) {
        Person person = getOneByPrimaryKey(personToAdd.getDocument());

        if (person == null) {
            personRepository.add(personToAdd);
        } else {
            throw new DuplicateException("Person with the same ID already exists.");
        }
    }

    @Override
    public Person update(Person personToUpdate) {
        Person person = getOneByPrimaryKey(personToUpdate.getDocument());

        if (person != null) {
            return personRepository.update(personToUpdate);
        }
        return null;
    }

    @Override
    public Person delete(Person personToDelete) {
        Person person = getOneByPrimaryKey(personToDelete.getDocument());

        if (person != null) {
            return personRepository.delete(personToDelete);
        }
        return null;
    }

    @Override
    public Person getOneByPrimaryKey(String document) {
        return personRepository.getOneByPrimaryKey(document);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.getAll();
    }
}
