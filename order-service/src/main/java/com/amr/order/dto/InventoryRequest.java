package com.amr.order.dto;

public record InventoryRequest(
        String skuCode,
        Integer quantity
) {
}
