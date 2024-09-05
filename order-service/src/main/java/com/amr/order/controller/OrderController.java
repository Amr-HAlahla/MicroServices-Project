package com.amr.order.controller;


import com.amr.order.dto.OrderRequest;
import com.amr.order.dto.OrderResponse;
import com.amr.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> placeOrder(
            @RequestBody @Valid OrderRequest request
    ) {
        return ResponseEntity.ok(orderService.placeOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderResponse> getOrder(
            @PathVariable("orderNumber") String orderNumber
    ) {
        return ResponseEntity.ok(orderService.findByOrderNumber(orderNumber));
    }

    @GetMapping("/exists/{orderNumber}")
    public ResponseEntity<Boolean> existsByOrderNumber(
            @PathVariable("orderNumber") String orderNumber
    ) {
        return ResponseEntity.ok(orderService.existsByOrderNumber(orderNumber));
    }
}
