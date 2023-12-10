package com.eCommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cart_product")
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartProductId;

    @ManyToOne()
    @JoinColumn(name = "cartId", referencedColumnName = "cartId")
    @JsonIgnore
    Cart cart;

    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;

    private int quantity = 1;
}
