package com.example.Tutorial.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.example.Tutorial.model.Person;

//interface is the blueprint of the class but contains abstract method (does not have any body)
//and the method body should be implemented in the class itself.
public interface personDao{

    //created a method which will return 1 for succesfull result (compulsory method to implement)
    int insertPerson(UUID id,Person person);

    //This is default method which will be used as default is there 
    //is no declaration of this method in the class then this will 
    //be used.
    default int insertPerson(Person person){
        UUID id = UUID.randomUUID(); //random generate some id
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();
    Optional<Person> selectPersonById(UUID id);
    int deletePersonById(UUID id);
    int updatePersonById(UUID id,Person person);


}
