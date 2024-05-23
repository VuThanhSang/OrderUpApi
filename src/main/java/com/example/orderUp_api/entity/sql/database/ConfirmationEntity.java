package com.example.orderUp_api.entity.sql.database;

import com.example.orderUp_api.constant.EntityConstant;
import com.example.orderUp_api.enums.ConfirmationCodeStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "confirmation")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationEntity {
    @Id
    @GenericGenerator(name = "confirmation_id", strategy = EntityConstant.TIME_ID_GENERATOR)
    @GeneratedValue(generator = "confirmation_id")
    private String id;

    private String code;

    private String email;

    @Enumerated(EnumType.STRING)
    private ConfirmationCodeStatus status;

    private Date expireAt;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}