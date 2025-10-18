package com.example.ecommerce.service;

import com.example.ecommerce.entity.Brand;
import com.example.ecommerce.repository.BrandRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BrandService {
    private final BrandRepository repository;
    public BrandService(BrandRepository repository) { this.repository = repository; }

    public List<Brand> getAll() { return repository.findAll(); }
    public List<Brand> getByCategory(Long categoryId) { return repository.findBrandsByCategory(categoryId); }
    public Brand save(Brand b) { return repository.save(b); }
    public Brand update(Long id, Brand b) { b.setId(id); return repository.save(b); }
    public void delete(Long id) { repository.deleteById(id); }
}
