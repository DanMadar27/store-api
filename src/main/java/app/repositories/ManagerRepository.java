package app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.models.Manager;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends MongoRepository<Manager, String> {

  public List<Manager> findAll();
  public Optional<Manager> findById(String id);
  
  public void deleteById(String id);
}