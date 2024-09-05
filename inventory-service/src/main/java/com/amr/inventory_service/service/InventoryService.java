package com.amr.inventory_service.service;


import com.amr.inventory_service.dto.InventoryRequest;
import com.amr.inventory_service.dto.InventoryResponse;
import com.amr.inventory_service.mapper.InventoryMapper;
import com.amr.inventory_service.repositoy.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(
            List<String> skuCodes, List<Integer> quantities) {

        return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
                .map(inventory -> {
                    int index = skuCodes.indexOf(inventory.getSkuCode());
                    int requestedQuantity = quantities.get(index);
                    return inventoryMapper.toInventoryResponse(
                            inventory.getSkuCode(),
                            inventory.getQuantity() > requestedQuantity
                    );
                })
                .toList();
    }
}
