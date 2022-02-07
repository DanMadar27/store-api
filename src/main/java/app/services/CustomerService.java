package app.services;

import java.util.List;
import java.util.Optional;

import app.models.Customer;
import app.models.Person;

public interface CustomerService {
  
  public List<Customer> findAll();
  public Optional<Customer> findById(String id);

  public Customer save(Person person);
  public Customer save(Customer customer);
  public void delete(String id);  
}