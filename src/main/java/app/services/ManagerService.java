package app.services;

import java.util.List;
import java.util.Optional;

import app.models.Manager;
import app.models.Person;

public interface ManagerService {
  
  public List<Manager> findAll();
  public Optional<Manager> findById(String id);

  public Manager save(Person person);
  public Manager save(Manager manager);
  public void delete(String id);  
}