package app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.models.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends MongoRepository<Supplier, String> {

  public List<Supplier> findAll();
  public Optional<Supplier> findById(String id);
  
  public void deleteById(String id);
}