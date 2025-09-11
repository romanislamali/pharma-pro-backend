package com.roman.pharma_pro_backend.modules.Product.entity;

import com.roman.pharma_pro_backend.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {

    @Column(length = 100)
    private String code;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 150)
    private String brand;

    @Column(name = "batch_no", length = 100)
    private String batchNo;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "purchase_price", precision = 12, scale = 2)
    private BigDecimal purchasePrice;

    @Column(name = "sale_price", precision = 12, scale = 2)
    private BigDecimal salePrice;

    private Integer quantity = 0;

    @Column(nullable = false, length = 50)
    private String status;
}
