package com.wishlist.exception;

public class ProductLimitExceededException extends RuntimeException {

    public ProductLimitExceededException(String message) {
        super(message);
    }
}
