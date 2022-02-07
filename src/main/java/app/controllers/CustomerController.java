package app.controllers;

import app.models.Customer;
import app.models.Person;
import app.models.User;
import app.services.CustomerService;
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
@RequestMapping("/customer")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private PersonService personService;

  @Autowired
  private UserService userService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping(value = "/")
  public List<Customer> getAll() {
    try {
      return customerService.findAll();
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @GetMapping(value = "/byId/{id}")
  public Customer getById(@PathVariable("id") String id) {
    Optional<Customer> customer;

    try {
      customer = customerService.findById(id);
      
      if(customer.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found");
      }

      return customer.get();
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }
  }

  @PostMapping(value = "/create")
  public ResponseEntity<?> create(@RequestBody Customer customer) {
    try {
      return new ResponseEntity<Customer>(customerService.save(customer), HttpStatus.OK);
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody Customer customer) {
    try {
      if(customerService.findById(customer.getId()).isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found");
      }

      return new ResponseEntity<Customer>(customerService.save(customer), HttpStatus.OK);
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
    Optional<Customer> customer;
    Optional<Person> person;
    Optional<User> user;

    try {
      customer = customerService.findById(id);
      if (customer.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found");
      }

      person = personService.findById(customer.get().getPerson().getId());
      if (person.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person Not Found");
      }

      user = userService.findById(customer.get().getUser().getId());
      if (user.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
      }

      personService.delete(person.get().getId());
      userService.delete(user.get().getId());
      customerService.delete(id);

      return new ResponseEntity<String>("Customer deleted successfully", HttpStatus.OK);
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }
    
    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }   
  }
}