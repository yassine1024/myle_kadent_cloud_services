package com.healthcaredental.reception.pointage;

public interface TotpService {

    /**
     * Generate a TOTP code using the shared secret.
     */
    String generateTotp();

    /**
     * Validate the provided TOTP code using the shared secret.
     */
    boolean validateTotp(String totpCode);

    /**
     * Returns the number of milliseconds remaining in the current TOTP time-step.
     */
    long getTimeRemaining();
}
