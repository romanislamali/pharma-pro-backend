package com.roman.pharma_pro_backend.modules.Product.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private UUID id;
    private String code;
    private String name;
    private String brand;
    private String batchNo;
    private LocalDate expiryDate;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private Integer quantity;
    private LocalDateTime createdAt;
}
