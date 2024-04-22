package com.wishlist.service;

import com.wishlist.exception.ProductNotFoundException;
import com.wishlist.exception.ProductLimitExceededException;
import com.wishlist.model.Wishlist;
import com.wishlist.model.entity.WishlistEntity;
import com.wishlist.repository.WishlistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class WishlistService {

    @Autowired
    private WishlistRepository repository;

    public Wishlist addProduct(Wishlist wishlist) {


        long totalProducts = repository.count();


        if (totalProducts >= 20) {
            throw new ProductLimitExceededException("The maximum product limit has been exceeded.");
        }


        WishlistEntity entity = repository.findByCustomerIdAndProductId(wishlist.getCustomerId(), wishlist.getProductId());

        if (ObjectUtils.isEmpty(entity)) {
            entity = new WishlistEntity();
            BeanUtils.copyProperties(wishlist, entity);
            repository.save(entity);
            wishlist.setId(entity.getId());
        } else {
            throw new ProductLimitExceededException("Product with ID " + wishlist.getProductId() + " already exists.");
        }

        return wishlist;

    }

    public void deleteProduct(String id) {
        repository.deleteById(id);
    }

    public List<Wishlist> findAllProductsByCostumerId(Long customerId) {


        List<WishlistEntity> entities = repository.findByCustomerId(customerId);

        if (entities.isEmpty()) {
            throw new ProductNotFoundException("Products not found with CustomerId: " + customerId);
        }

        List<Wishlist> products = new ArrayList<>();

        entities.forEach(e -> {
            Wishlist wishlist = new Wishlist();
            BeanUtils.copyProperties(e, wishlist);
            products.add(wishlist);
        });
        return products;
    }

    public Wishlist findProductById(String id) {
        Optional<WishlistEntity> entity = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id)));

        Wishlist wishlist = new Wishlist();
        BeanUtils.copyProperties(entity.get(), wishlist);

        return wishlist;
    }


}
