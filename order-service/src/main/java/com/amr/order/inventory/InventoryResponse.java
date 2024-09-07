package com.amr.order.inventory;

public record InventoryResponse(
        String skuCode,
        boolean isInStock
) {
}
