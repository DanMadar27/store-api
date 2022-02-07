package app.services;

import java.util.List;
import java.util.Optional;

import app.models.ProductSale;

public interface ProductSaleService {
  
  public List<ProductSale> findAll();
  public Optional<ProductSale> findById(String id);

  public ProductSale save(ProductSale productSale);
  public void delete(String id);  
  public void deleteByProduct(String productId);  
  public void softDeleteByProduct(String productId);  
}