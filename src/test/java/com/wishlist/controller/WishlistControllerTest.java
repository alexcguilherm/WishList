package com.wishlist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wishlist.model.NewProduct;
import com.wishlist.model.Product;
import com.wishlist.model.Wishlist;
import com.wishlist.service.WishlistService;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
public class WishlistControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper para converter objetos para JSON

    @MockBean
    private WishlistService service; // Você precisa mocar o serviço associado ao controller

    @Test
    public void testAddProductToWishlist() throws Exception {

        NewProduct newProduct = new NewProduct();
        newProduct.setCustomerId(7777L);

        Product product = new Product();
        product.setProductId(67890L);
        product.setName("Product Name");
        product.setDescription("Product Description");
        product.setPrice(99.99);
        product.setBrand("Product Brand");
        product.setModel("Product Model");
        product.setAvailable(true);
        product.setRating(4.5);
        newProduct.setProduct(product);

        given(service.addProduct(any(NewProduct.class))).willReturn(newProduct);

        mockMvc.perform(post("/wishlist/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId").value(7777))
                .andExpect(jsonPath("$.product.productId").value(67890))
                .andExpect(jsonPath("$.product.name").value("Product Name"))
                .andExpect(jsonPath("$.product.description").value("Product Description"))
                .andExpect(jsonPath("$.product.price").value(99.99))
                .andExpect(jsonPath("$.product.brand").value("Product Brand"))
                .andExpect(jsonPath("$.product.model").value("Product Model"))
                .andExpect(jsonPath("$.product.available").value(true))
                .andExpect(jsonPath("$.product.rating").value(4.5));
    }


    @Test
    public void testDeleteProductFromWishlist() throws Exception {

        Long customerId = 7777L;
        Long productId = 67890L;

        doNothing().when(service).deleteProduct(anyLong(), anyLong());

        mockMvc.perform(delete("/wishlist")
                        .param("customerId", String.valueOf(customerId))
                        .param("productId", String.valueOf(productId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    @Test
    public void testFindAllProductsByCustomerId() throws Exception {
        Long customerId = 7777L;

        Wishlist wishlist = new Wishlist();
        wishlist.setCustomerId(customerId);
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product1", "Description1", 10.0, "Brand1", "Model1", true, 4.0));
        products.add(new Product(2L, "Product2", "Description2", 20.0, "Brand2", "Model2", true, 4.5));
        wishlist.setProducts(products);

        when(service.findAllProductsByCostumerId(anyLong())).thenReturn(wishlist);

        mockMvc.perform(get("/wishlist/products/{customerId}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(customerId))
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.products.length()").value(products.size()))
                .andExpect(jsonPath("$.products[0].name").value("Product1"))
                .andExpect(jsonPath("$.products[0].description").value("Description1"))
                .andExpect(jsonPath("$.products[0].price").value(10.0))
                .andExpect(jsonPath("$.products[0].brand").value("Brand1"))
                .andExpect(jsonPath("$.products[0].model").value("Model1"))
                .andExpect(jsonPath("$.products[0].available").value(true))
                .andExpect(jsonPath("$.products[0].rating").value(4.0))
                .andExpect(jsonPath("$.products[1].name").value("Product2"))
                .andExpect(jsonPath("$.products[1].description").value("Description2"))
                .andExpect(jsonPath("$.products[1].price").value(20.0))
                .andExpect(jsonPath("$.products[1].brand").value("Brand2"))
                .andExpect(jsonPath("$.products[1].model").value("Model2"))
                .andExpect(jsonPath("$.products[1].available").value(true))
                .andExpect(jsonPath("$.products[1].rating").value(4.5));
    }


    @Test
    public void testFindProductById() throws Exception {
        Long customerId = 7777L;
        Long productId = 12345L;

        NewProduct newProduct = new NewProduct();
        newProduct.setCustomerId(customerId);

        Product product = new Product();
        product.setProductId(productId);
        product.setName("Product Name");
        product.setDescription("Product Description");
        product.setPrice(99.99);
        product.setBrand("Product Brand");
        product.setModel("Product Model");
        product.setAvailable(true);
        product.setRating(4.5);
        newProduct.setProduct(product);

        when(service.findProductById(anyLong(), anyLong())).thenReturn(newProduct);

        mockMvc.perform(get("/wishlist")
                        .param("customerId", String.valueOf(customerId))
                        .param("productId", String.valueOf(productId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(customerId))
                .andExpect(jsonPath("$.product.productId").value(productId))
                .andExpect(jsonPath("$.product.name").value("Product Name"))
                .andExpect(jsonPath("$.product.description").value("Product Description"))
                .andExpect(jsonPath("$.product.price").value(99.99))
                .andExpect(jsonPath("$.product.brand").value("Product Brand"))
                .andExpect(jsonPath("$.product.model").value("Product Model"))
                .andExpect(jsonPath("$.product.available").value(true))
                .andExpect(jsonPath("$.product.rating").value(4.5));
    }


}
