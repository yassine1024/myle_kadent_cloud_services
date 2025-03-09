package com.healthcaredental.reception.pointage;

import lombok.Data;

@Data
public class PointageValidationRequest {
    private String employeeId;
    private String totp;
    private String deviceName;
}