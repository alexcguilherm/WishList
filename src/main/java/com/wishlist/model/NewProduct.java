package com.wishlist.model;


import lombok.Data;

@Data
public class NewProduct {

    private Long customerId;

    private Product product;
}
