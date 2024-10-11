package dev.nhairlahovic.productservice.repository;

import dev.nhairlahovic.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
