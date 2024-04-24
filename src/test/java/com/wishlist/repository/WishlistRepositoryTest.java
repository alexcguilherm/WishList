package com.wishlist.repository;

import com.wishlist.model.Product;
import com.wishlist.model.entity.WishlistEntity;
import com.wishlist.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WishlistRepositoryTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private WishlistService wishlistService;

    public WishlistRepositoryTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByCustomerId() {
        Long customerId = 123L;

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId(1L);
        product1.setName("Product 1");
        products.add(product1);

        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setName("Product 2");
        products.add(product2);

        WishlistEntity expectedEntity = new WishlistEntity();
        expectedEntity.setCustomerId(customerId);
        expectedEntity.setProducts(products);

        when(wishlistRepository.findByCustomerId(customerId)).thenReturn(expectedEntity);

        WishlistEntity resultEntity = wishlistRepository.findByCustomerId(customerId);

        assertEquals(customerId, resultEntity.getCustomerId());
        assertEquals(products.size(), resultEntity.getProducts().size());
        assertEquals(product1, resultEntity.getProducts().get(0));
        assertEquals(product2, resultEntity.getProducts().get(1));
    }
}
