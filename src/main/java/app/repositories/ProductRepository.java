package app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

  public List<Product> findAll();
  public Optional<Product> findById(String id);
  
  public void deleteById(String id);
}