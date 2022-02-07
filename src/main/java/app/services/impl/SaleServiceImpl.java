package app.services.impl;

import app.models.Sale;
import app.repositories.SaleRepository;
import app.services.SaleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {
  
  @Autowired
  private SaleRepository saleRepository;

  @Override
  public List<Sale> findAll() {
    return saleRepository.findAll();
  }

  @Override
  public Optional<Sale> findById(String id) {
    return saleRepository.findById(id);
  }

  @Override
  public Sale save(Sale sale) {
    return saleRepository.save(sale);
  }

  @Override
  public void delete(String id) {
    saleRepository.deleteById(id);
  }  
}