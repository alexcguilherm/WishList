package com.wishlist.controller;


import com.wishlist.model.Wishlist;
import com.wishlist.service.WishlistService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService service;


//    @Operation(summary = "Add wishlist item", description = "Add a item from the wishlist.")
//    @PostMapping("/add")
//    public ResponseEntity<Wishlist> addProduct(@RequestBody Wishlist wishlist) {
//
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(service.addProduct(wishlist));
//
//    }


    @Operation(summary = "Add wishlist item", description = "Add a item from the wishlist.")
    @PostMapping("/add")
    public ResponseEntity<Wishlist> addProduct(@RequestBody Wishlist wishlist) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addProduct(wishlist));

    }

    @Operation(summary = "Delete wishlist item", description = "Deletes an item from the wishlist.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Wishlist> deleteProduct(@PathVariable String id) {

        service.deleteProduct(id);

        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "Find All items", description = "Find all items a customer's wish list.")
    @GetMapping("/products/{customerId}")
    public ResponseEntity<List<Wishlist>> findAllProductsByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(service.findAllProductsByCostumerId(customerId));

    }

    @Operation(summary = "Find product by Id", description = "Find product by id in wishlist.")
    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> findProductById(@PathVariable String id) {
        return ResponseEntity.ok(service.findProductById(id));

    }

}
