package com.wishlist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
