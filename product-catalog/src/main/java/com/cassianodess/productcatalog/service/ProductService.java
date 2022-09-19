package com.cassianodess.productcatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cassianodess.productcatalog.model.Product;
import com.cassianodess.productcatalog.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> list() {
        log.info("Product list successfully retrieved");
        return this.repository.findAll();
    }

    public Product getById(Long id) {
        return this.repository.findById(id).orElseThrow(() -> {
            log.error("id {} not found", id);
            throw new RuntimeException("id " + id + " not found");
        });
    }

    public Product create(Product product) {
        Product saved = this.repository.save(product);
        log.info("Product {} saved successfully", product);
        return saved;
    }

    public Product update(Long id, Product product) {

        Product old = this.repository.findById(id).orElseThrow(() -> {
            log.error("id {} not found", id);
            throw new RuntimeException("id " + id + " not found");
        });

        old.setName(product.getName());
        old.setPrice(product.getPrice());

        log.info("Product with id {} updated successfully", id);

        return this.repository.save(old);
    }

    public Product delete(Long id) {
        Product product = this.repository.findById(id).orElseThrow(() -> {
            log.error("id {} not found", id);
            throw new RuntimeException("id " + id + " not found");
        });

        this.repository.deleteById(id);
        log.info("Product with id {} deleted successfully", id);

        return product;
    }

}
