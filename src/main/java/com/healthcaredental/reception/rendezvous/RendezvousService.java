package com.healthcaredental.reception.rendezvous;

import com.healthcaredental.reception.cabinet.CabinetVisit;
import com.healthcaredental.reception.cabinet.CabinetVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RendezvousService {

    private final String METHOD_RENDEZVOUS = "STRICT_NBR_HOUR";
    private final RendezvousManagement rendezvousFIFO;
    private final RendezvousManagement rendezvousStrictNbrPatientHour;
    private final RendezvousManagement rendezvousUndefinedNbrPatientHour;
    @Autowired
    private  CabinetVisitRepository cabinetVisitRepository;


    public RendezvousService(RendezvousFIFO rendezvousFIFO, RendezvousStrictNbrPatientHour rendezvousStrictNbrPatientHour,
                             RendezvousUndefinedNbrPatient rendezvousUndefinedNbrPatient) {

        this.rendezvousFIFO = rendezvousFIFO;
        this.rendezvousStrictNbrPatientHour = rendezvousStrictNbrPatientHour;
        this.rendezvousUndefinedNbrPatientHour = rendezvousUndefinedNbrPatient;

    }

    @Autowired
    private RendezvousRepository rendezvousRepository;
    @Autowired
    private RendezvousPostPonedRepository rendezvousPostPonedRepository;

    public List<Rendezvous> getAllRendezvous(String cabinetId) {

        List<CabinetVisit> cabinetVisits = cabinetVisitRepository.findByCabinetId(cabinetId);
        List<Rendezvous> rendezvousList = new ArrayList<>();
        Set<String> processedPatientIds = new HashSet<>();

        for (CabinetVisit visit : cabinetVisits) {
            String patientId = visit.getPatient().getId();
            if (!processedPatientIds.contains(patientId)) {
                rendezvousRepository.findByPatientId(patientId)
                        .forEach(rendezvousList::add);
                processedPatientIds.add(patientId);
            }
        }

        return rendezvousList;
    }

    public Rendezvous getRendezvous(Long id) {
        return rendezvousRepository.findById(id).get();
    }

    public String addRendezvous(Rendezvous rendezvous) {

//        rendezvousRepository.save(rendezvous);
        if (this.METHOD_RENDEZVOUS.equals("FIFO")) {
            return rendezvousFIFO.addRendezvous(rendezvous, rendezvousRepository);
        } else if (this.METHOD_RENDEZVOUS.equals("STRICT_NBR_HOUR")) {
            return rendezvousStrictNbrPatientHour.addRendezvous(rendezvous, rendezvousRepository);

        } else if (this.METHOD_RENDEZVOUS.equals("UNDEFINED_NBR_HOUR")) {
            return rendezvousUndefinedNbrPatientHour.addRendezvous(rendezvous, rendezvousRepository);

        }
        return "";
    }

    public boolean updateRendezvous(String rendezvousId, String reason) {

        Rendezvous rendezvous = this.getRendezvous(Long.parseLong(rendezvousId));
        RendezvousPostPoned rendezvousPostPoned = new RendezvousPostPoned();
        rendezvousPostPoned.setReason(reason);
        rendezvousPostPoned.setRendezvous(rendezvous);
        rendezvousPostPonedRepository.save(rendezvousPostPoned);

        return true;
    }


    public void deleteRendezvous(Long id) {

        rendezvousRepository.deleteById(id);
    }

    public List<Rendezvous> getAllRendezvousByPatient(String patientId) {

        List<Rendezvous> rendezvous = new ArrayList<Rendezvous>();


        rendezvousRepository.findByPatientId(patientId)
                .forEach(rendezvous::add);

        return rendezvous;
    }

    public List<Rendezvous> getAllRendezvousByDoctor(String medecinId) {

        List<Rendezvous> rendezvous = new ArrayList<Rendezvous>();

        rendezvousRepository.findByMedecinId(medecinId)
                .forEach(rendezvous::add);
        return rendezvous;
    }

    public List<Rendezvous> getAllRendezvousByDate(String date) {

        List<Rendezvous> rendezvous = new ArrayList<Rendezvous>();

        rendezvousRepository.findByDateOrderByTimeAsc(date)
                .forEach(rendezvous::add);
        return rendezvous;
    }
}
