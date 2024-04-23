package com.wishlist.repository;

import com.wishlist.model.entity.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableMongoRepositories
@EnableJpaRepositories
public interface WishlistRepository extends CrudRepository<WishlistEntity, String> {

    WishlistEntity findByCustomerId(Long customerId);
}
