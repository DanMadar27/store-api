package app.services.impl;

import app.models.Person;
import app.repositories.PersonRepository;
import app.services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
  
  @Autowired
  private PersonRepository personRepository;

  @Override
  public List<Person> findAll() {
    return personRepository.findAll();
  }

  @Override
  public Optional<Person> findById(String id) {
    return personRepository.findById(id);
  }

  @Override
  public Person save(Person person) {
    return personRepository.save(person);
  }

  @Override
  public void delete(String id) {
    personRepository.deleteById(id);
  }  
}