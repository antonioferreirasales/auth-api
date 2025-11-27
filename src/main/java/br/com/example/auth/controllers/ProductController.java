package br.com.example.auth.controllers;

import br.com.example.auth.domain.product.Product;
import br.com.example.auth.domain.product.ProductRequestDTO;
import br.com.example.auth.domain.product.ProductResponseDTO;
import br.com.example.auth.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> postProduct(@RequestBody ProductRequestDTO body) {
        Product newProduct = new Product(body);
        this.repository.save(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponseDTO(newProduct));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> productList = this.repository.findAll().stream().map(ProductResponseDTO::new).toList();
        return ResponseEntity.ok(productList);
    }
}
