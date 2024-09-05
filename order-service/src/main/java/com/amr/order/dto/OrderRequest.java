package com.amr.order.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequest(
        List<OrderLineItemsDto> orderLineItemsDto
) {
}
