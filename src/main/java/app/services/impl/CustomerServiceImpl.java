package app.services.impl;

import app.models.Customer;
import app.models.Person;
import app.repositories.CustomerRepository;
import app.services.CustomerService;
import app.services.PersonService;
import app.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private PersonService personService;

  @Autowired
  private UserService userService;

  @Override
  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  @Override
  public Optional<Customer> findById(String id) {
    return customerRepository.findById(id);
  }

  @Override
  public Customer save(Person person) {
    Customer customer;
    
    customer = new Customer(personService.save(person));

    return customerRepository.save(customer);
  }

  @Override
  public Customer save(Customer customer) {
    personService.save(customer.getPerson());
    userService.save(customer.getUser());

    return customerRepository.save(customer);
  }

  @Override
  public void delete(String id) {
    customerRepository.deleteById(id);
  }  
}