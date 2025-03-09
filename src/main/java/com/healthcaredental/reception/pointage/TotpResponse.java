package com.healthcaredental.reception.pointage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotpResponse {
    // The generated TOTP code (or the QR code string to be displayed)
    private String totp;
    // The number of milliseconds remaining until the current TOTP code expires
    private long validUntil;
}
