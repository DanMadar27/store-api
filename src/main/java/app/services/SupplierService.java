package app.services;

import java.util.List;
import java.util.Optional;

import app.models.Supplier;

public interface SupplierService {
  
  public List<Supplier> findAll();
  public Optional<Supplier> findById(String id);

  public Supplier save(Supplier supplier);
  public void delete(String id);  
}