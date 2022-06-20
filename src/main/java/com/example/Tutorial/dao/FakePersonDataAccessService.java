package com.example.Tutorial.dao;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.Tutorial.model.Person;

// #Depedency Injector
@Repository("fakedao") // or @Component - This class will be instanciated as a bean so that we can use
                       // it in all the classes
public class FakePersonDataAccessService implements personDao {
    private static List<Person> DB = new ArrayList<>(); // DB is Database

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName())); // new Person will create a new object(also allocate space is memory)
                                                  // and add it to the DB
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        // check if user exist in DB with ID
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
        // find first will return first elmenet in the stream.
        // stream is the array of data(Person) which has same id's.
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if (personMaybe.isEmpty()) {
            return 0;
        } else {
            DB.remove(personMaybe.get());
        }
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {

        return selectPersonById(id).map(p -> {
            int indexOfPersonToUpdate = DB.indexOf(p);
            if (indexOfPersonToUpdate >= 0) { //person is present in the list
                DB.set(indexOfPersonToUpdate, new Person(id, person.getName()));
                return 1;
            }
            return 0;
        }).orElse(0); //if this wont work(selectPersonById) then orElse will run and return int value 0

    }

}
