package com.synergy.bokja.repository;

import com.synergy.bokja.entity.CycleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CycleRepository extends JpaRepository<CycleEntity, Long> {

    CycleEntity findByCyno(Long cyno);
}