package com.example.Tutorial.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Tutorial.model.Person;
import com.example.Tutorial.service.PersonService;
//In spring boot world we call controllers which will have implementation of HTTP requests

@RequestMapping("api/v1/person") // this is the end point for this API
@RestController // this annotation will define this
// class will be used as REST Controller
public class PersonController {

    private final PersonService personservice; // It should have interface but for now we are using class

    @Autowired
    public PersonController(PersonService personservice) {
        this.personservice = personservice;
    }

    // this method will serve as a post request
    @PostMapping // this will tell spring that this method will be used for post request
    public void addPerson(@Valid @NotNull @RequestBody Person person) {
        System.out.println("HI USER");
        personservice.addPerson(person);
    }

    // First Post something in the List
    @GetMapping // this will tell spring that this method will be used for get request
    public List<Person> getAllPeople() {
        System.out.println("Get USER");
        return personservice.getAllPeople();
    }

    @GetMapping(path = "/{id}") // - this will allow us to get "forward slash in the path" ie /
    // to get ID from the Url ie .
    // https://localhost:8080/api/v1/person/5d9f8f8f-8b8b-4f8b-8b8b-8b8b8b8b8b8b
    // 5d9f8f8f-8b8b-4f8b-8b8b-8b8b8b8b8b8b - it is some random id which was passed
    // in the url and we need to get it using annotation pathvariable("id")
    public Person getPersonById(@PathVariable("id") UUID id) // her we are getting/grabbing the id from the url
    {
        return personservice.getPersonById(id).orElse(null); // this function is of Optional Type and it will return
                                                             // null if the id is not found or else return Array
    }

    @DeleteMapping(path = "/{id}")
    public void deletePerson(@PathVariable("id") UUID id) {
        personservice.deletePerson(id);
    }

    @PutMapping(path = "/{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @RequestBody Person personToupdate) { // it will take a json
                                                                                                // body as a parameter
                                                                                                // to update.
        personservice.updatePerson(id, personToupdate);
    }

    // update and delete request
    // @PutMapping(path = "{studentId}")
    // public void updateStudent(@PathVariable("name") UUID studentId,
    // @RequestBody Student student) {
    // studentService.updateStudent(studentId, student);
    // }

    // @DeleteMapping("{studentId}")
    // public void deleteStudent(@PathVariable("studentId") UUID studentId) {
    // studentService.deleteStudent(studentId);
    // }

}
