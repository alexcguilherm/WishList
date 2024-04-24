package com.wishlist.service;

import com.wishlist.exception.NoContentException;
import com.wishlist.exception.ProductAlreadyExistsException;
import com.wishlist.exception.ProductNotFoundException;
import com.wishlist.exception.ProductLimitExceededException;
import com.wishlist.model.NewProduct;
import com.wishlist.model.Product;
import com.wishlist.model.Wishlist;
import com.wishlist.model.entity.WishlistEntity;
import com.wishlist.repository.WishlistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



@Service
public class WishlistService {

    @Autowired
    private WishlistRepository repository;

    public NewProduct addProduct(NewProduct newProduct) {

        WishlistEntity entity = repository.findByCustomerId(newProduct.getCustomerId());

        if (ObjectUtils.isEmpty(entity)) {
            entity = new WishlistEntity();
            entity.setCustomerId(newProduct.getCustomerId());
            entity.setProducts(Collections.singletonList(newProduct.getProduct()));

        } else {
            List<Product> products = entity.getProducts();

            if (!CollectionUtils.isEmpty(products)) {

                validateLimitExceeded(products);

                validateAlreadyExists(newProduct, products);

            }
            entity.getProducts().add(newProduct.getProduct());
        }

        repository.save(entity);

        return newProduct;

    }

    private static void validateAlreadyExists(NewProduct newProduct, List<Product> products) {
        List<Product> productList = products.stream().filter(p -> p.getProductId().equals(newProduct.getProduct().getProductId())).toList();

        if (!CollectionUtils.isEmpty(productList)) {
            throw new ProductAlreadyExistsException("Product with ID " + productList.get(0).getProductId() + " already exists.");
        }
    }

    private static void validateLimitExceeded(List<Product> products) {
        int totalProducts = products.size();

        if (totalProducts >= 20) {
            throw new ProductLimitExceededException("The maximum product limit has been exceeded.");
        }
    }

    public void deleteProduct(Long customerId, Long productId ) {
        WishlistEntity entity = repository.findByCustomerId(customerId);

        entity.getProducts().removeIf(product -> product.getProductId().equals(productId));

        repository.save(entity);
    }

    public Wishlist findAllProductsByCostumerId(Long customerId) {

        WishlistEntity entities = repository.findByCustomerId(customerId);


        if (ObjectUtils.isEmpty(entities)) {
            throw new ProductNotFoundException("Products not found with CustomerId: " + customerId);
        }

        if (entities.getProducts().isEmpty()) {
            throw new NoContentException();
        }

        Wishlist wishList = new Wishlist();

        List<Product> productList = new ArrayList<>();

        entities.getProducts().forEach(e -> {
            Product product = new Product();
            BeanUtils.copyProperties(e, product);
            productList.add(product);

        });
        wishList.setCustomerId(entities.getCustomerId());
        wishList.setProducts(productList);

        return wishList;
    }

    public NewProduct findProductById(Long customerId, Long productId) {
        WishlistEntity entity = repository.findByCustomerId(customerId);

        NewProduct newProduct = new NewProduct();

        if (ObjectUtils.isEmpty(entity)) {
            throw new ProductNotFoundException("Product not found with customerId: " + customerId);
        }

        if(ObjectUtils.isEmpty(entity.getProducts())) {
            throw new ProductNotFoundException("Product not found with customerId: " + customerId);
        }

        List<Product> products = entity.getProducts().stream().filter(product -> product.getProductId().equals(productId)).toList();

        if (products.isEmpty()) {
            throw new ProductNotFoundException("Product not found with producerId: " + productId);
        }

        newProduct.setCustomerId(entity.getCustomerId());
        newProduct.setProduct(products.get(0));


        return newProduct;
    }


}
