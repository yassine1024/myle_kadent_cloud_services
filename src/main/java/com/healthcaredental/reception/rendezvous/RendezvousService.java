package com.healthcaredental.reception.rendezvous;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RendezvousService {

    private final  String METHOD_RENDEZVOUS="FIFO";
    private RendezvousManagement rendezvousManagement;

    public RendezvousService() {

        if(this.METHOD_RENDEZVOUS.equals("FIFO")){
            rendezvousManagement = new RendezvousFIFO();
        }

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
        return rendezvousManagement.addRendezvous(rendezvous,rendezvousRepository);
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
