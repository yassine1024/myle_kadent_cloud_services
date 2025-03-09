package com.healthcaredental.reception.pointage;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TotpServiceImpl implements TotpService{

    @Value("${totp.secret}")
    private String totpSecret;

    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();

    @Override
    public String generateTotp() {
        // Implement code generation if needed.
        // For example, if you want to use the secret to generate a TOTP:
        return String.valueOf(gAuth.getTotpPassword(totpSecret));
    }

    @Override
    public boolean validateTotp(String totpCode) {
        try {
            int code = Integer.parseInt(totpCode);
            return gAuth.authorize(totpSecret, code);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public long getTimeRemaining() {
        // Example: return remaining time in the current 30-second window.
        long currentTimeMillis = System.currentTimeMillis();
        return 30000 - (currentTimeMillis % 30000);
    }
}
