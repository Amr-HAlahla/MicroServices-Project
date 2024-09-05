package com.amr.inventory_service.dto;

public record InventoryRequest(
        String skuCode,
        Integer quantity
) {
}
