package app.controllers;

import app.models.Customer;
import app.models.ProductSale;
import app.models.Sale;
import app.services.CustomerService;
import app.services.ProductSaleService;
import app.services.SaleService;

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
@RequestMapping("/sale")
public class SaleController {

  @Autowired
  private SaleService saleService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private ProductSaleService productSaleService;

  public SaleController(SaleService saleService) {
    this.saleService = saleService;
  }

  @GetMapping(value = "/")
  public List<Sale> getAll() {
    try {
      return saleService.findAll();
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @GetMapping(value = "/byId/{id}")
  public Sale getById(@PathVariable("id") String id) {
    Optional<Sale> sale;

    try {
      sale = saleService.findById(id);
      
      if(sale.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale Not Found");
      }

      return sale.get();
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
    Sale sale;
    String customerId;
    String[] productSaleIds = new String[20];
    Optional<Customer> customer;
    List<ProductSale> productSales;
    Optional<ProductSale> productSale;;
    double price;

    try {
      customerId = data.get("customerId");
      productSaleIds = data.get("productSalesId").split(",", 20);

      customer = customerService.findById(customerId);

      if(customer.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found");
      }

      productSales = new ArrayList<ProductSale>();
      
      for (String id : productSaleIds) {
        productSale = productSaleService.findById(id);
        
        if(productSale.isEmpty()) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ProdutSale Not Found");
        }
        
        if(productSale.get().getIsSold()) {
          throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
            "ProdutSale Had Been Sold");
        }

        productSale.get().setIsSold(true);
        productSaleService.save(productSale.get());
        
        productSales.add(productSale.get());
      }

      price = productSales.stream().mapToDouble(ps -> ps.getProduct().getPrice()).sum();

      sale = new Sale(customer.get(), productSales, price);

      return new ResponseEntity<Sale>(saleService.save(sale), HttpStatus.OK);
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
    Optional<Sale> sale;
    
    try {
      sale = saleService.findById(id);
      if (sale.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale Not Found");
      }

      for (ProductSale productSale : sale.get().getProductSales()) {
        productSale.setIsSold(false);
        productSaleService.save(productSale);
      }

      saleService.delete(id);
 
      return new ResponseEntity<String>("Sale deleted successfully", HttpStatus.OK);
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }
    
    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }   
  }
}