package com.amr.order.dto;

import java.util.List;

public record OrderResponse(
        Long id,
        String orderNumber,
        List<OrderLineItemsDto> orderLineItemsDto
) {
}
