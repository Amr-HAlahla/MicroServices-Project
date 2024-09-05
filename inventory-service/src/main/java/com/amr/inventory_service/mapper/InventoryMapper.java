package com.amr.inventory_service.mapper;

import com.amr.inventory_service.dto.InventoryRequest;
import com.amr.inventory_service.dto.InventoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryMapper {
    public InventoryResponse toInventoryResponse(String skuCode, boolean isInStock) {
        return new InventoryResponse(
                skuCode,
                isInStock
        );
    }

    public InventoryRequest toInventoryRequest(String skuCode, Integer quantity) {
        return new InventoryRequest(
                skuCode,
                quantity
        );
    }
}
