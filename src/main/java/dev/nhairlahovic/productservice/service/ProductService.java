package dev.nhairlahovic.productservice.service;

import dev.nhairlahovic.productservice.dto.ProductRequestDto;
import dev.nhairlahovic.productservice.model.Product;
import dev.nhairlahovic.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(ProductRequestDto requestDto) {
        Product product = ProductRequestDto.toEntity(requestDto);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductRequestDto requestDto) {
        Product product = getProductById(id);
        product.setName(requestDto.getName());
        product.setDescription(requestDto.getDescription());
        product.setPrice(requestDto.getPrice());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
