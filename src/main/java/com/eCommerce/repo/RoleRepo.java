package com.eCommerce.repo;

import com.eCommerce.entity.Role;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleName(String roleName);


}
