package com.jamilis.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class UserEntity {
    @Id
    private String id;
    private Instant created;
    private Instant lastLogin;
    private String token;
    private Boolean isActive;
    private String name;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = PhoneEntity.class)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<PhoneEntity> phones;
}
