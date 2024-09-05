package com.amr.order.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.amr.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderNumber(String orderNumber);

    Boolean existsByOrderNumber(String orderNumber);
}
