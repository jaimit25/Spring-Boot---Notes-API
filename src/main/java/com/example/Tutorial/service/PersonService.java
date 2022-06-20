package com.example.Tutorial.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.Tutorial.dao.personDao;
import com.example.Tutorial.model.Person;

@Service // Defines that this class is a service we can also use @Component
public class PersonService {

    private final personDao persondao;

    @Autowired // This will inject the dependency
    public PersonService(@Qualifier("fakedao") personDao persondao) {
        this.persondao = persondao;
    }

    public int addPerson(Person person) {
        return persondao.insertPerson(person);// using the default method
    }

    public List<Person> getAllPeople() {
        return persondao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return persondao.selectPersonById(id);
    }

    public int deletePerson(UUID id) {
        return persondao.deletePersonById(id);
    }

    public int updatePerson(UUID id, Person person) {
        return persondao.updatePersonById(id, person);
    }

}
