package app.services;

import java.util.List;
import java.util.Optional;

import app.models.User;

public interface UserService {
  
  public List<User> findAll();
  public Optional<User> findById(String id);

  public User save(User user);
  public void delete(String id);  
}