package com.amr.order.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvalidOrderRequestException extends RuntimeException {
    private final String message;
}

