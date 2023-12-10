package com.eCommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_role",uniqueConstraints = @UniqueConstraint(name = "role_unq",
columnNames = "roleName"))
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;


    String roleName;

}
