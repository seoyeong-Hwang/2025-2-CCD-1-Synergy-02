package com.synergy.bokja.repository;

import com.synergy.bokja.entity.UserMedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMedicineRepository extends JpaRepository<UserMedicineEntity, Long> {

    UserMedicineEntity findByUmno(Long umno);
}