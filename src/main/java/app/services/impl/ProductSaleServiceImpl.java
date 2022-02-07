package app.services.impl;

import app.models.ProductSale;
import app.repositories.ProductSaleRepository;
import app.services.ProductSaleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;

@Service
public class ProductSaleServiceImpl implements ProductSaleService {
  
  @Autowired
  private ProductSaleRepository productSaleRepository;

  @Override
  public List<ProductSale> findAll() {
    return productSaleRepository.findAll();
  }

  @Override
  public Optional<ProductSale> findById(String id) {
    return productSaleRepository.findById(id);
  }

  @Override
  public ProductSale save(ProductSale productSale) {
    return productSaleRepository.save(productSale);
  }

  @Override
  public void delete(String id) {
    productSaleRepository.deleteById(id);
  }

  @Override
  public void deleteByProduct(String productId) {
    List<ProductSale> productSales;

    productSales = findAll();
    
    for (ProductSale productSale : productSales) {
      if(productSale.getProduct().getId().equals(productId)) {
        delete(productSale.getId());
      }
    }
  }

  @Override
  public void softDeleteByProduct(String productId) {
    List<ProductSale> productSales;

    productSales = findAll();
    
    for (ProductSale productSale : productSales) {
      if(productSale.getProduct().getId().equals(productId)) {
        productSale.setDeletedAt(new Timestamp(System.currentTimeMillis()).toString());
        save(productSale);
      }
    }
  }
}