package com.amr.order.mapper;

import com.amr.order.dto.OrderLineItemsDto;
import com.amr.order.dto.OrderRequest;
import com.amr.order.dto.OrderResponse;
import com.amr.order.model.Order;
import com.amr.order.model.OrderLineItems;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
                .orderLineItems(
                        orderRequest.orderLineItemsDto().stream()
                                .map(this::toOrderLineItems)
                                .toList()
                )
                .build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getOrderLineItems().stream()
                        .map(this::toOrderLineItemsDto)
                        .toList()
        );
    }

    public OrderLineItems toOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .id(orderLineItemsDto.id())
                .skuCode(orderLineItemsDto.skuCode())
                .price(orderLineItemsDto.price())
                .quantity(orderLineItemsDto.quantity())
                .build();
    }

    public OrderLineItemsDto toOrderLineItemsDto(OrderLineItems orderLineItems) {
        return new OrderLineItemsDto(
                orderLineItems.getId(),
                orderLineItems.getSkuCode(),
                orderLineItems.getPrice(),
                orderLineItems.getQuantity()
        );
    }
}
