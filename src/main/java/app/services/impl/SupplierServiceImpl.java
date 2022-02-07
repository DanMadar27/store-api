package app.services.impl;

import app.models.Supplier;
import app.repositories.SupplierRepository;
import app.services.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {
  
  @Autowired
  private SupplierRepository supplierRepository;

  @Override
  public List<Supplier> findAll() {
    return supplierRepository.findAll();
  }

  @Override
  public Optional<Supplier> findById(String id) {
    return supplierRepository.findById(id);
  }

  @Override
  public Supplier save(Supplier supplier) {
    return supplierRepository.save(supplier);
  }

  @Override
  public void delete(String id) {
    supplierRepository.deleteById(id);
  }  
}