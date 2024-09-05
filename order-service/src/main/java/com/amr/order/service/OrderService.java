package com.amr.order.service;


import com.amr.order.dto.InventoryResponse;
import com.amr.order.dto.OrderRequest;
import com.amr.order.dto.OrderResponse;
import com.amr.order.exception.InvalidOrderRequestException;
import com.amr.order.exception.ProductOutOfStockException;
import com.amr.order.mapper.OrderMapper;
import com.amr.order.model.Order;
import com.amr.order.model.OrderLineItems;
import com.amr.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final WebClient webClient;

    public String placeOrder(OrderRequest request) {
        Order order = orderMapper.toOrder(request);
        order.setOrderNumber(UUID.randomUUID().toString());
        List<String> skuCodes = order.getOrderLineItems().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();
        List<Integer> quantities = order.getOrderLineItems().stream()
                .map(OrderLineItems::getQuantity)
                .toList();
        if (skuCodes.size() != quantities.size()) {
            throw new InvalidOrderRequestException("skuCodes and quantities must have the same size");
        }
        // Check Inventory Service for product availability
        InventoryResponse[] inventoryResponses = webClient.get()
                .uri("http://localhost:8060/api/inventory",
                        uriBuilder -> uriBuilder
                                .queryParam("skuCodes", skuCodes)
                                .queryParam("quantities", quantities)
                                .build()
                )
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        assert inventoryResponses != null;
        boolean AllProductsInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::isInStock);
        if (!AllProductsInStock) {
            throw new ProductOutOfStockException("Some products are out of stock");
        }
        orderRepository.save(order);
        return order.getOrderNumber();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    public OrderResponse findByOrderNumber(String orderNumber) {
        return orderMapper.toOrderResponse(
                orderRepository.findByOrderNumber(orderNumber)
        );
    }

    public Boolean existsByOrderNumber(String orderNumber) {
        return orderRepository.existsByOrderNumber(orderNumber);
    }
}
