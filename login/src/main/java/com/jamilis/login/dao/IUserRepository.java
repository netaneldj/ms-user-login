package com.jamilis.login.dao;

import com.jamilis.login.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmailAndIsActiveTrue(String email);

    Optional<UserEntity> findByTokenAndIsActiveTrue(String token);
}
