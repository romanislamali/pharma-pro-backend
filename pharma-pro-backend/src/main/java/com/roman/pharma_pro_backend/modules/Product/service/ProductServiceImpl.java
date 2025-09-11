package com.roman.pharma_pro_backend.modules.Product.service;

import com.roman.pharma_pro_backend.common.ApiRequest;
import com.roman.pharma_pro_backend.common.util.FilterPaginationHandler;
import com.roman.pharma_pro_backend.modules.Product.entity.Product;
import com.roman.pharma_pro_backend.modules.Product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FilterPaginationHandler<Product> handler;

    public ProductServiceImpl(ProductRepository productRepository, FilterPaginationHandler<Product> handler) {
        this.productRepository = productRepository;
        this.handler = handler;
    }

    @Override
    public ResponseEntity<?> addOrUpdateProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findByName(product.getName());

        if (product.getId() != null) {
            Optional<Product> productOpt = productRepository.findById(product.getId());
            if (productOpt.isEmpty()) {
                return ResponseEntity
                        .badRequest()
                        .body("Product not found with id " + product.getId());
            }

            if (existingProduct.isPresent() && !existingProduct.get().getId().equals(product.getId())) {
                return ResponseEntity
                        .badRequest()
                        .body("Product name already taken");
            }

            productRepository.save(product);
            return ResponseEntity.ok("Product successfully updated");

        } else {
            if (existingProduct.isPresent()) {
                return ResponseEntity
                        .badRequest()
                        .body("Product name already taken");
            }

            if (product.getName() == null || product.getName().isEmpty()) {
                return ResponseEntity
                        .badRequest()
                        .body("Product name cannot be empty");
            }

            productRepository.save(product);
            return ResponseEntity.ok("New product add successful");
        }
    }

    @Override
    public Page<Product> getAllProducts(ApiRequest request) {
        return handler.handle(request, productRepository, Product.class);
    }
}
