package com.healthcaredental.reception.rendezvous;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RendezvousService {

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

    public void addRendezvous(Rendezvous rendezvous) {

        rendezvousRepository.save(rendezvous);
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
