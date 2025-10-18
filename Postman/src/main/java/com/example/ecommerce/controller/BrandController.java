package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Brand;
import com.example.ecommerce.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/brands")

public class BrandController {
    @Autowired
    private final BrandService service;
    public BrandController(BrandService service) { this.service = service; }

    @GetMapping
    public List<Brand> getAll() { return service.getAll(); }

    @GetMapping("/by-category/{categoryId}")
    public List<Brand> getByCategory(@PathVariable Long categoryId) { return service.getByCategory(categoryId); }

    @PostMapping
    public Brand create(@RequestBody Brand b) { return service.save(b); }

    @PutMapping("/{id}")
    public Brand update(@PathVariable Long id, @RequestBody Brand b) { return service.update(id, b); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
