package app.controllers;

import app.models.Product;
import app.services.ProductSaleService;
import app.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductSaleService productSaleService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping(value = "/")
  public List<Product> getAll() {
    try {
      return productService.findAll();
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @GetMapping(value = "/byId/{id}")
  public Product getById(@PathVariable("id") String id) {
    Optional<Product> product;

    try {
      product = productService.findById(id);
      
      if(product.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
      }

      return product.get();
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }
  }

  @GetMapping(value = "/quantity/{id}")
  public int quantity(@PathVariable("id") String id) {
    Optional<Product> product;
    
    try {
      product = productService.findById(id);
      
      if(product.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
      }

      return productService.quantity(id);
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }
  }
  
  @PostMapping(value = "/create")
  public ResponseEntity<?> create(@RequestBody Product product) {
    try {
      return new ResponseEntity<Product>(productService.save(product), HttpStatus.OK);
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody Product product) {
    try {
      if(productService.findById(product.getId()).isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
      }

      return new ResponseEntity<Product>(productService.save(product), HttpStatus.OK);
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }
  }

  @DeleteMapping(value = "/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    Optional<Product> product;
    
    try {
      product = productService.findById(id);
      if (product.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
      }

      productSaleService.deleteByProduct(id);
      productService.delete(id);
      return new ResponseEntity<String>("Product deleted successfully", HttpStatus.OK);
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }
    
    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }   
  }

  @DeleteMapping(value = "/softDelete/{id}")
  public ResponseEntity<?> softDelete(@PathVariable String id) {
    Optional<Product> product;
    
    try {
      product = productService.findById(id);
      if (product.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
      }

      productSaleService.softDeleteByProduct(id);
      productService.softDelete(id);
      return new ResponseEntity<String>("Product deleted successfully", HttpStatus.OK);
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }
    
    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }   
  }
}