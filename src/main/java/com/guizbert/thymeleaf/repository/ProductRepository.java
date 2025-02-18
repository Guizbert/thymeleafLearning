package com.guizbert.thymeleaf.repository;


import com.guizbert.thymeleaf.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
