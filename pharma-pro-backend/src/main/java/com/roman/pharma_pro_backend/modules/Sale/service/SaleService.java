package com.roman.pharma_pro_backend.modules.Sale.service;

import com.roman.pharma_pro_backend.modules.Sale.dto.SaleDTO;
import com.roman.pharma_pro_backend.modules.Sale.entity.Sale;

import java.util.List;
import java.util.UUID;

public interface SaleService {
    Sale createSale(Sale sale);
    List<Sale> getSales();
}
