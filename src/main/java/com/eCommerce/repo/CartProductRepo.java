package com.eCommerce.repo;

import com.eCommerce.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartProductRepo extends JpaRepository<CartProduct,Long> {


    Optional<CartProduct> findByProductProductIdAndCartUserUserId(Long aLong,Long blong);
}
