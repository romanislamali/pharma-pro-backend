package com.roman.pharma_pro_backend.modules.Product.service;

import com.roman.pharma_pro_backend.modules.Product.entity.Product;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
}
