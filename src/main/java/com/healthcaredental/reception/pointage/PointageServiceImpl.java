package com.healthcaredental.reception.pointage;

import com.healthcaredental.reception.employee.Employee;
import com.healthcaredental.reception.employee.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PointageServiceImpl implements PointageService {

    private final PointageRepository pointageRepository;
    private final EmployeeRepository employeeRepository;


    @Override
    public Pointage checkIn(String employeeId, LocalTime arrivalTime, String deviceName) {
        LocalDate today = LocalDate.now();
        Optional<Pointage> existingRecord = pointageRepository.findByEmployeeIdAndPointageDate(employeeId, today);
        if (existingRecord.isPresent()) {
            throw new IllegalStateException("Check-in already recorded for today.");
        }

        // Retrieve the employee (assumes employeeRepository.findById returns an Optional<Employee>)
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found."));

        Pointage pointage = new Pointage();
        pointage.setEmployee(employee);
        pointage.setPointageDate(today);
        pointage.setArrivalTime(arrivalTime);
        pointage.setDeviceName(deviceName); // Record the device used for check-in

        // departureTime remains null until check-out
        return pointageRepository.save(pointage);
    }

    @Override
    public Pointage checkOut(String employeeId, LocalTime departureTime, String deviceName) {
        LocalDate today = LocalDate.now();
        Pointage pointage = pointageRepository.findByEmployeeIdAndPointageDate(employeeId, today)
                .orElseThrow(() -> new IllegalStateException("No check-in record found for today."));

        if (pointage.getDepartureTime() != null) {
            throw new IllegalStateException("Check-out already recorded for today.");
        }

        // Ensure that the device used for check-out is the same as the one used for check-in.
        if (!pointage.getDeviceName().equals(deviceName)) {
            throw new IllegalStateException("Check-out must be performed from the same device used for check-in.");
        }

        if (departureTime.isBefore(pointage.getArrivalTime())) {
            throw new IllegalArgumentException("Departure time must be after arrival time.");
        }

        pointage.setDepartureTime(departureTime);
        return pointageRepository.save(pointage);
    }
}