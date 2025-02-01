package com.healthcaredental.reception.employee.medecin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedecinService {

    private final MedecinRepository medecinRepository;

    public List<Medecin> getMedecinsByCabinet(String cabinetId) {
        return medecinRepository.findByCabinetId(cabinetId);
    }
}
