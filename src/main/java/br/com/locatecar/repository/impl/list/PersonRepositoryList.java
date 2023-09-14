package br.com.locatecar.repository.impl.list;

import br.com.locatecar.model.Person;
import br.com.locatecar.repository.api.IPersonRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonRepositoryList implements IPersonRepository<Person> {

    List<Person> people;

    public PersonRepositoryList() {
        this.people = new ArrayList<>();
    }

    @Override
    public void add(Person personToAdd) {
        this.people.add(personToAdd);
    }

    @Override
    public Person update(Person updatedPerson) {
        for (int i = 0; i < this.people.size(); i++) {
            Person person = this.people.get(i);
            if (person.getDocument().equalsIgnoreCase(updatedPerson.getDocument())) {
                this.people.set(i, updatedPerson);
                return updatedPerson;
            }
        }
        return null;
    }

    @Override
    public Person delete(Person personToDelete) {
        Person person = getOneByPrimaryKey(personToDelete.getDocument());

        if (person != null) {
            people.remove(personToDelete);
        }
        return personToDelete;
    }

    @Override
    public Person getOneByPrimaryKey(String document) {
        for (Person person : this.people) {
            if (person.getDocument().equalsIgnoreCase(document)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public List<Person> getAll() {
        return Collections.unmodifiableList(people);
    }
}
