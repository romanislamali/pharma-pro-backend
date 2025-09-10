package com.roman.pharma_pro_backend.modules.Sale.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleItemDTO {
    private UUID id;
    private UUID productId;
    private Integer qty;
    private BigDecimal price;
}
