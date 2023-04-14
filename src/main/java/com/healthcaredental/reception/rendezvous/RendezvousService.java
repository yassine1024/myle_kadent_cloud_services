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


    public RendezvousService(RendezvousFIFO rendezvousFIFO, RendezvousStrictNbrPatientHour rendezvousStrictNbrPatientHour) {

        this.rendezvousFIFO = rendezvousFIFO;
        this.rendezvousStrictNbrPatientHour = rendezvousStrictNbrPatientHour;

    }

    @Autowired
    private RendezvousRepository rendezvousRepository;

    public List<Rendezvous> getAllRendezvous() {

        List<Rendezvous> rendezvous = new ArrayList<Rendezvous>();

        rendezvousRepository.findAll()
                .forEach(rendezvous::add);
        return rendezvous;

    }

    public Rendezvous getRendezvous(String id) {
        return null;
    }

    public String addRendezvous(Rendezvous rendezvous) {

//        rendezvousRepository.save(rendezvous);
        if(this.METHOD_RENDEZVOUS.equals("FIFO")){
            return rendezvousFIFO.addRendezvous(rendezvous, rendezvousRepository);
        } else if (this.METHOD_RENDEZVOUS.equals("STRICT_NBR_HOUR")) {
            return rendezvousStrictNbrPatientHour.addRendezvous(rendezvous, rendezvousRepository);

        }
        return "";
    }

    public void updateRendezvous(Rendezvous rendezvous) {

        rendezvousRepository.save(rendezvous);
    }

    public void deleteRendezvous(String id) {

        rendezvousRepository.deleteById(id);
    }

    public List<Rendezvous> getAllRendezvousByPatient(String patientId) {

        List<Rendezvous> rendezvous = new ArrayList<Rendezvous>();


        rendezvousRepository.findByPatientId(patientId)
                .forEach(rendezvous::add);

        return rendezvous;
    }
}
