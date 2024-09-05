package com.amr.inventory_service.controller;


import com.amr.inventory_service.dto.InventoryRequest;
import com.amr.inventory_service.dto.InventoryResponse;
import com.amr.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> isInStock(
            @RequestParam List<String> skuCodes, @RequestParam List<Integer> quantities) {
        return ResponseEntity.ok(inventoryService.isInStock(skuCodes, quantities));
    }
}
