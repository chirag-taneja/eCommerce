package com.eCommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Getter
@Setter
@ToString(exclude = "seller")
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_product",uniqueConstraints = @UniqueConstraint(
        name = "product uniqe",
        columnNames = "productName"
))
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;


    private String productName;

    private double Price;

    @ManyToOne()
    @JoinColumn(name = "seller_id", referencedColumnName = "userId", updatable = false)
    @JsonIgnore
    private User seller;


    @ManyToOne()
    @JoinColumn(name = "categoryId" ,referencedColumnName = "categoryId")
    @JsonIgnore
    private Category category;

}
