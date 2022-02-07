package app.controllers;

import app.models.Supplier;
import app.services.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

  @Autowired
  private SupplierService supplierService;

  public SupplierController(SupplierService supplierService) {
    this.supplierService = supplierService;
  }

  @GetMapping(value = "/")
  public List<Supplier> getAll() {
    try {
      return supplierService.findAll();
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @GetMapping(value = "/byId/{id}")
  public Supplier getById(@PathVariable("id") String id) {
    Optional<Supplier> supplier;

    try {
      supplier = supplierService.findById(id);
      
      if(supplier.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier Not Found");
      }

      return supplier.get();
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }
  }

  @PostMapping(value = "/create")
  public ResponseEntity<?> create(@RequestBody Supplier supplier) {
    try {
      return new ResponseEntity<Supplier>(supplierService.save(supplier), HttpStatus.OK);
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody Supplier supplier) {
    try {
      if(supplierService.findById(supplier.getId()).isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier Not Found");
      }

      return new ResponseEntity<Supplier>(supplierService.save(supplier), HttpStatus.OK);
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
    Optional<Supplier> supplier;
    
    try {
      supplier = supplierService.findById(id);
      if (supplier.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier Not Found");
      }

      supplierService.delete(id);
      return new ResponseEntity<String>("Supplier deleted successfully", HttpStatus.OK);
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }
    
    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }   
  }
}