package com.eCommerce.repo;

import com.eCommerce.entity.CartProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartProductRepoTest {

    @Autowired
    CartProductRepo cartProductRepo;

    @Test
    public void test()
    {
        CartProduct cartProduct = cartProductRepo.findByProductProductIdAndCartUserUserId(1L, 1L).get();
        System.out.println(cartProduct.getCartProductId());
        System.out.println(cartProduct.getProduct());
    }
}