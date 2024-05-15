package com.example.orderUp_api.entity.sql.database;

import com.example.orderUp_api.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

import static com.example.orderUp_api.constant.EntityConstant.TIME_ID_GENERATOR;


@Entity
@Table(name = "role")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, unique = true)
    private RoleEnum roleName;

    @ManyToMany(mappedBy = "roleList")
    private Set<UserEntity> userList;
}
