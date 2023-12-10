package com.eCommerce.repo;

import com.eCommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {


    Optional<Product> findBySellerUserId(Long aLong);


    Set<Product> findByProductNameContainingOrCategoryCategoryNameContaining(String name, String cName);


}
