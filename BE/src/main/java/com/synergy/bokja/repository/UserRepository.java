package com.synergy.bokja.repository;

import com.synergy.bokja.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUno(Long uno);
}
