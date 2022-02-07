package app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.models.Supply;

import java.util.List;
import java.util.Optional;

public interface SupplyRepository extends MongoRepository<Supply, String> {

  public List<Supply> findAll();
  public Optional<Supply> findById(String id);
  
  public void deleteById(String id);
}