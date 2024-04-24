package com.wishlist.service;

import com.wishlist.exception.NoContentException;
import com.wishlist.exception.ProductAlreadyExistsException;
import com.wishlist.exception.ProductLimitExceededException;
import com.wishlist.exception.ProductNotFoundException;
import com.wishlist.model.NewProduct;
import com.wishlist.model.Product;
import com.wishlist.model.Wishlist;
import com.wishlist.model.entity.WishlistEntity;
import com.wishlist.repository.WishlistRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class WishlistServiceTest {

    @Mock
    private WishlistRepository repository;

    @InjectMocks
    private WishlistService service;

    @Test
    public void testAddProduct_WithLimitExceeded() {

        NewProduct newProduct = new NewProduct();
        newProduct.setCustomerId(7777L);
        Product product = new Product();
        newProduct.setProduct(product);

        WishlistEntity entity = new WishlistEntity();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            products.add(new Product());
        }
        entity.setProducts(products);

        when(repository.findByCustomerId(7777L)).thenReturn(entity);

        assertThrows(ProductLimitExceededException.class, () -> service.addProduct(newProduct));
        verify(repository, never()).save(any());
    }

    @Test
    public void testAddProduct_WithProductAlreadyExists() {

        Long productId = 12345L;

        NewProduct newProduct = new NewProduct();
        newProduct.setCustomerId(7777L);
        Product product = new Product();
        product.setProductId(productId);
        newProduct.setProduct(product);

        WishlistEntity entity = new WishlistEntity();
        entity.setProducts(Collections.singletonList(product));

        when(repository.findByCustomerId(7777L)).thenReturn(entity);

        assertThrows(ProductAlreadyExistsException.class, () -> service.addProduct(newProduct));
        verify(repository, never()).save(any());
    }

    @Test
    public void testAddProduct_Successful() {

        NewProduct newProduct = new NewProduct();
        newProduct.setCustomerId(7777L);
        Product product = new Product();
        newProduct.setProduct(product);

        WishlistEntity entity = new WishlistEntity();

        when(repository.findByCustomerId(7777L)).thenReturn(null);
        when(repository.save(any(WishlistEntity.class))).thenReturn(entity);

        NewProduct result = service.addProduct(newProduct);

        verify(repository, times(1)).save(any());
        assertEquals(newProduct, result);
    }

    @Test
    public void testDeleteProduct() {

        Long customerId = 7777L;
        Long productId = 12345L;

        WishlistEntity entity = new WishlistEntity();
        entity.setCustomerId(customerId);
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId(productId);
        products.add(product);
        entity.setProducts(products);

        when(repository.findByCustomerId(customerId)).thenReturn(entity);
        when(repository.save(any(WishlistEntity.class))).thenReturn(entity);

        service.deleteProduct(customerId, productId);

        verify(repository).save(entity);
        assertEquals(0, entity.getProducts().size());
    }


    @Test
    public void testFindAllProductsByCostumerId_WithProductsFound() {
        Long customerId = 7777L;

        WishlistEntity entity = new WishlistEntity();
        entity.setCustomerId(customerId);
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        entity.setProducts(products);

        when(repository.findByCustomerId(customerId)).thenReturn(entity);

        Wishlist result = service.findAllProductsByCostumerId(customerId);

        assertEquals(customerId, result.getCustomerId());
        assertEquals(products.size(), result.getProducts().size());
    }

    @Test
    public void testFindAllProductsByCostumerId_WithProductsNotFound() {
        Long customerId = 7777L;

        when(repository.findByCustomerId(customerId)).thenReturn(null);

        assertThrows(ProductNotFoundException.class, () -> service.findAllProductsByCostumerId(customerId));
    }

    @Test
    public void testFindAllProductsByCostumerId_WithNoContent() {
        Long customerId = 7777L;

        WishlistEntity entity = new WishlistEntity();
        entity.setCustomerId(customerId);
        entity.setProducts(new ArrayList<>());

        when(repository.findByCustomerId(customerId)).thenReturn(entity);

        assertThrows(NoContentException.class, () -> service.findAllProductsByCostumerId(customerId));
    }

    @Test
    public void testFindProductById_WithProductFound() {

        Long customerId = 7777L;
        Long productId = 12345L;

        WishlistEntity entity = new WishlistEntity();
        entity.setCustomerId(customerId);
        Product product = new Product();
        product.setProductId(productId);
        List<Product> products = new ArrayList<>();
        products.add(product);
        entity.setProducts(products);

        when(repository.findByCustomerId(customerId)).thenReturn(entity);

        NewProduct result = service.findProductById(customerId, productId);

        assertEquals(customerId, result.getCustomerId());
        assertEquals(productId, result.getProduct().getProductId());
    }

    @Test
    public void testFindProductById_WithProductNotFound_CustomerId() {
        Long customerId = 7777L;
        Long productId = 12345L;

        WishlistEntity entity = new WishlistEntity();

        when(repository.findByCustomerId(customerId)).thenReturn(entity);

        assertThrows(ProductNotFoundException.class, () -> service.findProductById(customerId, productId));
    }

    @Test
    public void testFindProductById_WithProductNotFound_ProductId() {
        Long customerId = 7777L;
        Long productId = 12345L;

        WishlistEntity entity = new WishlistEntity();
        entity.setCustomerId(customerId);
        entity.setProducts(new ArrayList<>());

        when(repository.findByCustomerId(customerId)).thenReturn(entity);

        assertThrows(ProductNotFoundException.class, () -> service.findProductById(customerId, productId));
    }


}
