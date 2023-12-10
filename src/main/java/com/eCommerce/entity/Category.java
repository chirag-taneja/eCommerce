package com.eCommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_category",uniqueConstraints = @UniqueConstraint(
        name = "category uniqe",
        columnNames = "categoryName"
))
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;


    private String categoryName;

}
