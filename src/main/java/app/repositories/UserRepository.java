package app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

  public List<User> findAll();
  public Optional<User> findById(String id);
  Boolean existsByUsername(String username);
  
  public void deleteById(String id);
}