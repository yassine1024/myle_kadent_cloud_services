package com.healthcaredental.reception.metrics;

import com.healthcaredental.reception.Patient.PatientRepository;
import com.healthcaredental.reception.employee.medecin.MedecinRepository;
import com.healthcaredental.reception.rendezvous.Rendezvous;
import com.healthcaredental.reception.rendezvous.RendezvousRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricsService {

    private final PatientRepository patientRepository;
    private final RendezvousRepository rendezvousRepository;
    private final MedecinRepository medecinRepository;

    public long getTotalPatients(String cabinetId) {
        return patientRepository.countByCabinetId(cabinetId);
    }

    public long getTotalAppointments(String cabinetId) {
        return rendezvousRepository.countByCabinetId(cabinetId);
    }

    public long getTotalDoctors(String cabinetId) {
        return medecinRepository.countByCabinetId(cabinetId);
    }

    public List<Rendezvous> fetchUpcomingAppointments(String cabinetId) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("now:"+now);
        return rendezvousRepository.findByCabinetIdAndDateAfter(cabinetId, now);
    }

    public List<String> fetchNotifications(String cabinetId) {
        // Implement your logic to fetch notifications
        // This is a placeholder implementation
        return List.of("Notification 1", "Notification 2");
    }
}