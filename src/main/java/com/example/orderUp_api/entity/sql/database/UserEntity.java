package com.example.orderUp_api.entity.sql.database;

import com.example.orderUp_api.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.orderUp_api.entity.sql.database.identifier.StringPrefixedSequenceGenerator;
import com.example.orderUp_api.entity.sql.database.order.OrderBillEntity;
import com.example.orderUp_api.enums.Gender;
import com.example.orderUp_api.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.example.orderUp_api.constant.EntityConstant.SEQUENCE_ID_GENERATOR;

@Entity
@Table(name = "user")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
//        @GenericGenerator(name = "user_id", strategy = TIME_ID_GENERATOR)
    @Id
    @GenericGenerator(name = "user_id", strategy = SEQUENCE_ID_GENERATOR, parameters = {
            @Parameter(name = StringPrefixedSequenceGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "U"),
            @Parameter(name = StringPrefixedSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
    })
    @GeneratedValue(generator = "user_id")
    private String id;

    @Column(name = "avatar_id", unique = true)
    private String avatarId;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "role", nullable = false)
    private RoleEnum role;
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private java.sql.Date birthDate;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "auth_type")
    private String authType;

    @Column(name = "coin", columnDefinition = "BIGINT DEFAULT 0")
    private Long coin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roleList;

    // =================================================================
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<AddressEntity> addressList;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<OrderBillEntity> orderBillList;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<CoinHistoryEntity> coinHistoryList;

    public String getId() {
        return String.valueOf(id);
    }
}
