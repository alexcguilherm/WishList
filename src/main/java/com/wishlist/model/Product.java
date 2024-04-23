package com.wishlist.model;

import lombok.Data;

@Data
public class Product {
    private Long productId;

    private String name;

    private String description;

    private Double price;
    private String brand;

    private String model;
    private Boolean available;
    private Double rating;
}
