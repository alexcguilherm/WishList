package com.wishlist.model;


import com.wishlist.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {

    private Long customerId;

    private List<Product> products;
}
