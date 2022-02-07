package app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.models.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends MongoRepository<Person, String> {

  public List<Person> findAll();
  public Optional<Person> findById(String id);
  
  public void deleteById(String id);
}