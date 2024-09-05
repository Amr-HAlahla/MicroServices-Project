package com.amr.inventory_service.dto;

public record InventoryResponse(
        String skuCode,
        boolean isInStock
) {
}
