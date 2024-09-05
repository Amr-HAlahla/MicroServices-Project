package com.amr.order.dto;

public record InventoryResponse(
        String skuCode,
        boolean isInStock
) {
}
