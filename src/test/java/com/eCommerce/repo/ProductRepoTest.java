package com.eCommerce.repo;

import com.eCommerce.entity.Category;
import com.eCommerce.entity.Product;
import com.eCommerce.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CategoryRepo categoryRepo;


    @Test
    public void saveProduct()
    {
        User user = userRepo.findById(1L).get();
        if (user==null)
        {
            throw new RuntimeException("User Not Found With this id" );
        }

        Product product=Product.builder()
                .productName("hankey")
                .Price(20.26)
                .seller(user)
                .build();

        productRepo.save(product);
    }

    @Test
    public void findBySellerId()
    {
        Product product = productRepo.findBySellerUserId(1L).get();
        System.out.println(product);
    }


    //add category in post method

    @Test
    public void updateCategory()
    {
        Product product = productRepo.findBySellerUserId(1L).get();
        Category category = categoryRepo.findById(1L).get();
        product.setCategory(category);
        productRepo.save(product);
    }
}