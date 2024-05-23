package com.example.orderUp_api.entity.sql.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import static com.example.orderUp_api.constant.EntityConstant.TIME_ID_GENERATOR;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token")
public class TokenEntity {

    @Id
    @GenericGenerator(name = "token_id", strategy = TIME_ID_GENERATOR)
    @GeneratedValue(generator = "token_id")
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String token;

    private boolean loggedOut;
}