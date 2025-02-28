package com.healthcaredental.reception.pointage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/api/pointage")
@RequiredArgsConstructor
public class PointageController {

    // Inject your TOTP service
    private final TotpService totpService;
    private final PointageService pointageService;


    // This endpoint returns the current TOTP (or a QR code string that encodes the TOTP)
    @GetMapping("/totp")
    public ResponseEntity<TotpResponse> getTotp() {
        String totp = totpService.generateTotp();
        long validUntil = totpService.getTimeRemaining();
        return ResponseEntity.ok(new TotpResponse(totp, validUntil));
    }

    @PostMapping("/checkin")
    public ResponseEntity<String> checkIn(@RequestBody PointageValidationRequest request) {
        // Validate the TOTP code for the employee.
        boolean valid = totpService.validateTotp(request.getTotp());
        if (!valid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid TOTP for check-in.");
        }
        // Use the current time for check-in.
        LocalTime now = LocalTime.now();
        try {
            Pointage pointage = pointageService.checkIn(request.getEmployeeId(), now, request.getDeviceName());
            return ResponseEntity.ok("Check-in successful at " + pointage.getArrivalTime());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkOut(@RequestBody PointageValidationRequest request) {
        // Validate the TOTP code for the employee.
        boolean valid = totpService.validateTotp(request.getTotp());
        if (!valid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid TOTP for check-out.");
        }
        // Use the current time for check-out.
        LocalTime now = LocalTime.now();
        try {
            Pointage pointage = pointageService.checkOut(request.getEmployeeId(), now, request.getDeviceName());
            return ResponseEntity.ok("Check-out successful at " + pointage.getDepartureTime());
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}

