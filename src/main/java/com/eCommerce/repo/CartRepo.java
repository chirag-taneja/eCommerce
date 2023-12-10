package com.eCommerce.repo;

import com.eCommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {


    List<Cart> findAllByUserUserId(long uid);

    boolean existsByUserUserId(long id);


    Optional<Cart> findByUserUserId(Long aLong);
}
