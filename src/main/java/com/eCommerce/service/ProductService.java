package com.eCommerce.service;

import com.eCommerce.dto.ProductDto;
import com.eCommerce.entity.Category;
import com.eCommerce.entity.Product;
import com.eCommerce.entity.User;
import com.eCommerce.repo.CategoryRepo;
import com.eCommerce.repo.ProductRepo;
import com.eCommerce.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    UserRepo userRepo;

    @Autowired
    public void setCategoryRepo(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    CategoryRepo categoryRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    public Product saveProduct(ProductDto productDto, String username) {

        User user = userRepo.findByUserName(username).get();

        Category category = categoryRepo.findById(productDto.categoryId()).get();

        Product product=Product.builder()
                .productName(productDto.productName())
                .seller(user)
                .Price(productDto.price())
                .category(category)
                .build();

        return productRepo.save(product);
    }

    public Set<Product> findBYName(String name) {
        return productRepo.findByProductNameContainingOrCategoryCategoryNameContaining(name,name);
    }

    public Product getAllProductBySellerId(long sellerId) {
        return productRepo.findBySellerUserId(sellerId).get();
    }
}
