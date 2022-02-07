package app.services;

import java.util.List;
import java.util.Optional;

import app.models.Product;

public interface ProductService {
  
  public List<Product> findAll();
  public Optional<Product> findById(String id);
  public int quantity(String id);

  public Product save(Product product);
  public void delete(String id);  
  public void softDelete(String id);
}