package app.controllers;

import app.models.Manager;
import app.models.Person;
import app.models.User;
import app.services.ManagerService;
import app.services.PersonService;
import app.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manager")
public class ManagerController {

  @Autowired
  private ManagerService managerService;

  @Autowired
  private PersonService personService;

  @Autowired
  private UserService userService;

  public ManagerController(ManagerService managerService) {
    this.managerService = managerService;
  }

  @GetMapping(value = "/")
  public List<Manager> getAll() {
    try {
      return managerService.findAll();
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @GetMapping(value = "/byId/{id}")
  public Manager getById(@PathVariable("id") String id) {
    Optional<Manager> manager;

    try {
      manager = managerService.findById(id);
      
      if(manager.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manager Not Found");
      }

      return manager.get();
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }
  }

  @PostMapping(value = "/create")
  public ResponseEntity<?> create(@RequestBody Manager manager) {
    try {
      return new ResponseEntity<Manager>(managerService.save(manager), HttpStatus.OK);
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody Manager manager) {
    try {
      if(managerService.findById(manager.getId()).isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manager Not Found");
      }

      return new ResponseEntity<Manager>(managerService.save(manager), HttpStatus.OK);
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
    Optional<Manager> manager;
    Optional<Person> person;
    Optional<User> user;

    try {
      manager = managerService.findById(id);
      if (manager.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manager Not Found");
      }

      person = personService.findById(manager.get().getPerson().getId());
      if (person.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person Not Found");
      }

      user = userService.findById(manager.get().getUser().getId());
      if (user.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
      }

      personService.delete(person.get().getId());
      userService.delete(user.get().getId());
      managerService.delete(id);
      
      return new ResponseEntity<String>("Manager deleted successfully", HttpStatus.OK);
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }
    
    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }   
  }
}