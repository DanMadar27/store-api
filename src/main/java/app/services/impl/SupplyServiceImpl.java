package app.services.impl;

import app.models.Supply;
import app.repositories.SupplyRepository;
import app.services.SupplyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplyServiceImpl implements SupplyService {
  
  @Autowired
  private SupplyRepository supplyRepository;

  @Override
  public List<Supply> findAll() {
    return supplyRepository.findAll();
  }

  @Override
  public Optional<Supply> findById(String id) {
    return supplyRepository.findById(id);
  }

  @Override
  public Supply save(Supply supply) {
    return supplyRepository.save(supply);
  }

  @Override
  public void delete(String id) {
    supplyRepository.deleteById(id);
  }  
}