package com.wishlist.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;


import com.wishlist.model.Product;
import jakarta.persistence.Entity;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Entity
@Document(collection = "wishlist")
public class WishlistEntity {

    @Id
    @JsonProperty("_id")
    private Long customerId;

    List<Product> products;

}
