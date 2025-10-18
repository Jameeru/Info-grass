package com.example.ecommerce.service;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    public CategoryService(CategoryRepository repository) { this.repository = repository; }

    public List<Category> getAll() { return repository.findAll(); }
    public Category save(Category c) { return repository.save(c); }
    public Category update(Long id, Category c) { c.setId(id); return repository.save(c); }
    public void delete(Long id) { repository.deleteById(id); }
}
