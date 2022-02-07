package app.controllers;

import app.models.Product;
import app.models.ProductSale;
import app.services.ProductSaleService;
import app.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/productSale")
public class ProductSaleController {

  @Autowired
  private ProductSaleService productSaleService;

  @Autowired
  private ProductService productService;

  public ProductSaleController(ProductSaleService productSaleService) {
    this.productSaleService = productSaleService;
  }

  @GetMapping(value = "/")
  public List<ProductSale> getAll() {
    try {
      return productSaleService.findAll();
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @GetMapping(value = "/byId/{id}")
  public ProductSale getById(@PathVariable("id") String id) {
    Optional<ProductSale> productSale;

    try {
      productSale = productSaleService.findById(id);
      
      if(productSale.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ProductSale Not Found");
      }

      return productSale.get();
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }
  }

  @PostMapping(value = "/create")
  public ResponseEntity<?> create(@RequestBody Map<String, String> data) {
    String productId;
    int quantity;
    List<ProductSale> productSales;
    Optional<Product> product;

    try {
      productId = data.get("productId");
      quantity = Integer.parseInt(data.get("quantity"));

      product = productService.findById(productId);

      if(product.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
      }

      productSales = new ArrayList<ProductSale>();

      for (int i = 0; i < quantity; i++) {
        productSales.add(productSaleService.save(new ProductSale(product.get())));
      }

      return new ResponseEntity<List<ProductSale>>(productSales, HttpStatus.OK);
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody ProductSale productSale) {
    try {
      if(productSaleService.findById(productSale.getId()).isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ProductSale Not Found");
      }

      return new ResponseEntity<ProductSale>(productSaleService.save(productSale), HttpStatus.OK);
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
    Optional<ProductSale> productSale;
    
    try {
      productSale = productSaleService.findById(id);
      if (productSale.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ProductSale Not Found");
      }

      productSaleService.delete(id);
      return new ResponseEntity<String>("ProductSale deleted successfully", HttpStatus.OK);
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }
    
    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }   
  }
}