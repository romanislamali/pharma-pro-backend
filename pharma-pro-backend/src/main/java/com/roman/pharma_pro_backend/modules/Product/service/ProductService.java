package com.roman.pharma_pro_backend.modules.Product.service;

import com.roman.pharma_pro_backend.common.ApiRequest;
import com.roman.pharma_pro_backend.modules.Product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<?> addOrUpdateProduct(Product product);
    Page<Product> getAllProducts(ApiRequest request);
}
