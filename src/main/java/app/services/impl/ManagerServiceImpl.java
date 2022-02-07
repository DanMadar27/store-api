package app.services.impl;

import app.models.Manager;
import app.models.Person;
import app.repositories.ManagerRepository;
import app.services.ManagerService;
import app.services.PersonService;
import app.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {

  @Autowired
  private ManagerRepository managerRepository;

  @Autowired
  private PersonService personService;

  @Autowired
  private UserService userService;

  @Override
  public List<Manager> findAll() {
    return managerRepository.findAll();
  }

  @Override
  public Optional<Manager> findById(String id) {
    return managerRepository.findById(id);
  }

  @Override
  public Manager save(Person person) {
    Manager manager;
    
    manager = new Manager(personService.save(person));

    return managerRepository.save(manager);
  }

  @Override
  public Manager save(Manager manager) {
    personService.save(manager.getPerson());
    userService.save(manager.getUser());

    return managerRepository.save(manager);
  }

  @Override
  public void delete(String id) {
    managerRepository.deleteById(id);
  }  
}