package com.wishlist.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Entity
@Document(collection = "wishlist")
public class WishlistEntity {

    @Id
    private String id;
    private Long customerId;
    private Long productId;

    @JsonProperty("name")
    private String name;


    @JsonProperty("description")
    private String description;


    @JsonProperty("price")
    private Double price;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("model")
    private String model;

    @JsonProperty("available")

    private Boolean available;

    @JsonProperty("rating")
    private Double rating;

}
