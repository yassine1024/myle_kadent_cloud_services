package com.healthcaredental.reception.cabinet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabinetVisitRepository extends JpaRepository<CabinetVisit, Long> {
    List<CabinetVisit> findByCabinetId(String cabinetId);
}