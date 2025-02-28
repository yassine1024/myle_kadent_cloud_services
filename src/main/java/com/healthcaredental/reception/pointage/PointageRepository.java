package com.healthcaredental.reception.pointage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PointageRepository extends JpaRepository<Pointage, Long> {
    Optional<Pointage> findByEmployeeIdAndPointageDate(String employeeId, LocalDate pointageDate);
}
