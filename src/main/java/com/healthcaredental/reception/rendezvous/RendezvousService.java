package com.healthcaredental.reception.rendezvous;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RendezvousService {

    private final  String METHOD_RENDEZVOUS="STRICT_NBR_HOUR";
    private final RendezvousManagement rendezvousFIFO;
    private final RendezvousManagement rendezvousStrictNbrPatientHour;
    private final RendezvousManagement rendezvousUndefinedNbrPatientHour;


    public RendezvousService(RendezvousFIFO rendezvousFIFO, RendezvousStrictNbrPatientHour rendezvousStrictNbrPatientHour,
                             RendezvousUndefinedNbrPatient rendezvousUndefinedNbrPatient) {

        this.rendezvousFIFO = rendezvousFIFO;
        this.rendezvousStrictNbrPatientHour = rendezvousStrictNbrPatientHour;
        this.rendezvousUndefinedNbrPatientHour = rendezvousUndefinedNbrPatient;

    }

    @Autowired
    private RendezvousRepository rendezvousRepository;

    public List<Rendezvous> getAllRendezvous() {

        List<Rendezvous> rendezvous = new ArrayList<Rendezvous>();

        rendezvousRepository.findAll()
                .forEach(rendezvous::add);
        return rendezvous;

    }

    public Rendezvous getRendezvous(Long id) {
        return rendezvousRepository.findById(id).get();
    }

    public String addRendezvous(Rendezvous rendezvous) {

//        rendezvousRepository.save(rendezvous);
        if(this.METHOD_RENDEZVOUS.equals("FIFO")){
            return rendezvousFIFO.addRendezvous(rendezvous, rendezvousRepository);
        } else if (this.METHOD_RENDEZVOUS.equals("STRICT_NBR_HOUR")) {
            return rendezvousStrictNbrPatientHour.addRendezvous(rendezvous, rendezvousRepository);

        }else if (this.METHOD_RENDEZVOUS.equals("UNDEFINED_NBR_HOUR")) {
            return rendezvousUndefinedNbrPatientHour.addRendezvous(rendezvous, rendezvousRepository);

        }
        return "";
    }

    public void updateRendezvous(Rendezvous rendezvous) {

        rendezvousRepository.save(rendezvous);
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
