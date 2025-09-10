package com.roman.pharma_pro_backend.modules.Sale.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {
    private UUID id;
    private String invoiceNo;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    private List<SaleItemDTO> saleItems;
}
