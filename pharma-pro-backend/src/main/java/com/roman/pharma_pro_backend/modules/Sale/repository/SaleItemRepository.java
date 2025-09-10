package com.roman.pharma_pro_backend.modules.Sale.repository;

import com.roman.pharma_pro_backend.modules.Sale.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, UUID>, JpaSpecificationExecutor<SaleItem> {
}
