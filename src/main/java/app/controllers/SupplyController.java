package app.controllers;

import app.models.Supply;
import app.services.SupplyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supply")
public class SupplyController {

  @Autowired
  private SupplyService supplyService;

  public SupplyController(SupplyService supplyService) {
    this.supplyService = supplyService;
  }

  @GetMapping(value = "/")
  public List<Supply> getAll() {
    try {
      return supplyService.findAll();
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @GetMapping(value = "/byId/{id}")
  public Supply getById(@PathVariable("id") String id) {
    Optional<Supply> supply;

    try {
      supply = supplyService.findById(id);
      
      if(supply.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supply Not Found");
      }

      return supply.get();
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }
  }

  @PostMapping(value = "/create")
  public ResponseEntity<?> create(@RequestBody Supply supply) {
    try {
      return new ResponseEntity<Supply>(supplyService.save(supply), HttpStatus.OK);
    }

    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  @PutMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody Supply supply) {
    try {
      if(supplyService.findById(supply.getId()).isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supply Not Found");
      }

      return new ResponseEntity<Supply>(supplyService.save(supply), HttpStatus.OK);
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
    Optional<Supply> supply;
    
    try {
      supply = supplyService.findById(id);
      if (supply.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supply Not Found");
      }

      supplyService.delete(id);
      return new ResponseEntity<String>("Supply deleted successfully", HttpStatus.OK);
    }

    catch (ResponseStatusException e) {
      throw new ResponseStatusException(e.getStatus(), e.getMessage());
    }
    
    catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()); 
    }   
  }
}