package com.healthcaredental.reception.pointage;

import com.healthcaredental.reception.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "pointage", uniqueConstraints = {
        // Enforce one record per employee per day.
        @UniqueConstraint(name = "unique_employee_date", columnNames = {"employee_id", "pointage_date"})
})
public class Pointage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "pointage_date", nullable = false)
    private LocalDate pointageDate;

    @Column(name = "arrival_time", nullable = false)
    private LocalTime arrivalTime;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    // New attribute to store the device name used for check-in/out
    @Column(name = "device_name")
    private String deviceName;
}
