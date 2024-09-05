package com.amr.inventory_service.repositoy;

import com.amr.inventory_service.dto.InventoryRequest;
import com.amr.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findBySkuCodeIn(Collection<String> skuCode);
}
