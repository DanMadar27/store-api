package app.controllers;

import app.models.Person;
import app.services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping(value = "/")
  public List<Person> getAll() {
    try {
      return personService.findAll();
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @GetMapping(value = "/byId/{id}")
  public Person getById(@PathVariable("id") String id) {
    Optional<Person> person;

    try {
      person = personService.findById(id);
      
      if(person.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person Not Found");
      }

      return person.get();
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }
  }

  @PostMapping(value = "/create")
  public ResponseEntity<?> create(@RequestBody Person person) {
    try {
      return new ResponseEntity<Person>(personService.save(person), HttpStatus.OK);
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody Person person) {
    try {
      if(personService.findById(person.getId()).isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person Not Found");
      }

      return new ResponseEntity<Person>(personService.save(person), HttpStatus.OK);
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }
  }

  @DeleteMapping(value = "/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    Optional<Person> person;
    
    try {
      person = personService.findById(id);
      if (person.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person Not Found");
      }

      personService.delete(id);
      return new ResponseEntity<String>("Person deleted successfully", HttpStatus.OK);
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }
    
    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }   
  }
}