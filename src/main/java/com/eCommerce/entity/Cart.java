package com.eCommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    private double totalAmount;

    @OneToOne()
    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    @JsonIgnore
    private User user;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "cart")
    List<CartProduct> cartProducts;

}
