package app.services;

import java.util.List;
import java.util.Optional;

import app.models.Supply;

public interface SupplyService {
  
  public List<Supply> findAll();
  public Optional<Supply> findById(String id);

  public Supply save(Supply supply);
  public void delete(String id);  
}