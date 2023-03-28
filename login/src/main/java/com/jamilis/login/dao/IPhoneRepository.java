package com.jamilis.login.dao;

import com.jamilis.login.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhoneRepository extends JpaRepository<PhoneEntity, Integer> {
}
