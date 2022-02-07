package app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.models.ProductSale;

import java.util.List;
import java.util.Optional;

public interface ProductSaleRepository extends MongoRepository<ProductSale, String> {

  public List<ProductSale> findAll();
  public Optional<ProductSale> findById(String id);
  
  public void deleteById(String id);
}