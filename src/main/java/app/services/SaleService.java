package app.services;

import java.util.List;
import java.util.Optional;

import app.models.Sale;

public interface SaleService {
  
  public List<Sale> findAll();
  public Optional<Sale> findById(String id);

  public Sale save(Sale sale);
  public void delete(String id);  
}