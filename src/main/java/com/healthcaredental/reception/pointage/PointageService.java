package com.healthcaredental.reception.pointage;

import java.time.LocalTime;

public interface PointageService {
    /**
     * Records a check-in for the given employee at the provided arrival time.
     *
     * @param employeeId  the ID of the employee
     * @param arrivalTime the arrival time
     * @return the saved Pointage record
     * @throws IllegalStateException if a check-in already exists for today
     */
    Pointage checkIn(String employeeId, LocalTime arrivalTime, String deviceName);

    /**
     * Records a check-out for the given employee at the provided departure time.
     *
     * @param employeeId   the ID of the employee
     * @param departureTime the departure time
     * @return the updated Pointage record
     * @throws IllegalStateException if no check-in record exists for today or if a check-out is already recorded
     * @throws IllegalArgumentException if the departure time is before the recorded arrival time
     */
    Pointage checkOut(String employeeId, LocalTime departureTime, String deviceName);

}
