package app.services;

import java.util.List;
import java.util.Optional;

import app.models.Person;

public interface PersonService {
  
  public List<Person> findAll();
  public Optional<Person> findById(String id);

  public Person save(Person person);
  public void delete(String id);  
}