package app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {

  public List<Customer> findAll();
  public Optional<Customer> findById(String id);
  
  public void deleteById(String id);
}