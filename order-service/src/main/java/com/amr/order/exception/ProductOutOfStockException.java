package com.amr.order.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductOutOfStockException extends RuntimeException {
    private final String message;
}
