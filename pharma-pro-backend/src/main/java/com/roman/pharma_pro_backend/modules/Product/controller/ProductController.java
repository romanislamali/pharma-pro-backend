package com.roman.pharma_pro_backend.modules.Product.controller;

import com.roman.pharma_pro_backend.common.ApiPaths;
import com.roman.pharma_pro_backend.common.ApiRequest;
import com.roman.pharma_pro_backend.common.Services;
import com.roman.pharma_pro_backend.modules.Product.entity.Product;
import com.roman.pharma_pro_backend.modules.Product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.API+ Services.PRODUCT)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(ApiPaths.SAVE)
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        return productService.addOrUpdateProduct(product);
    }

    @PostMapping(ApiPaths.UPDATE)
    public ResponseEntity<?> updateProduct(@RequestBody Product request) {
        return productService.addOrUpdateProduct(request);
    }

    @PostMapping(ApiPaths.CHANGE_STATUS)
    public ResponseEntity<?> changeProductStatus(@RequestBody Product request) {
        return productService.addOrUpdateProduct(request);
    }

    @PostMapping(ApiPaths.LIST)
    public ResponseEntity<?> getUsers(@RequestBody ApiRequest request) {
        if(request.getService().equalsIgnoreCase(Services.PRODUCT)){
            return ResponseEntity.ok(productService.getAllProducts(request));
        }
        return ResponseEntity.badRequest().body("Unsupported service");
    }
}
