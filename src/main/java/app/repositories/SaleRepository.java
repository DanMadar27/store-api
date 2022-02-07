package app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.models.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends MongoRepository<Sale, String> {

  public List<Sale> findAll();
  public Optional<Sale> findById(String id);
  
  public void deleteById(String id);
}