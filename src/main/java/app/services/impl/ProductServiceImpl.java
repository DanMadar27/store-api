package app.services.impl;

import app.models.Product;
import app.models.ProductSale;
import app.repositories.ProductRepository;
import app.services.ProductSaleService;
import app.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;

@Service
public class ProductServiceImpl implements ProductService {
  
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProductSaleService productSaleService;

  @Override
  public List<Product> findAll() {
    return productRepository.findAll();
  }

  @Override
  public Optional<Product> findById(String id) {
    return productRepository.findById(id);
  }

  @Override
  public int quantity(String id) {
    List<ProductSale> productSales;
    int quantity;

    productSales = productSaleService.findAll();
    quantity = 0;

    for (ProductSale productSale : productSales) {
      if(productSale.getProduct().getId().equals(id) && !productSale.getIsSold()) {
        quantity += 1;
      }
    }

    return quantity;
  }

  @Override
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Override
  public void delete(String id) {
    productRepository.deleteById(id);
  }
  
  @Override
  public void softDelete(String id) {
    Product product;

    product = productRepository.findById(id).get();
    product.setDeletedAt(new Timestamp(System.currentTimeMillis()).toString());
    save(product);
  }
}