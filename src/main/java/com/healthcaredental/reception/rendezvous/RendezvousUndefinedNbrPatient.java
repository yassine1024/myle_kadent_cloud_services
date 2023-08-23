package com.healthcaredental.reception.rendezvous;

import org.springframework.stereotype.Component;

@Component
public class RendezvousUndefinedNbrPatient implements RendezvousManagement {
    @Override
    public void getListRendezvousManagement() {

    }

    @Override
    public String addRendezvous(Rendezvous rendezvous, RendezvousRepository rendezvousRepository) {

        if(!rendezvousRepository.ifPatientAlreadyAdded(rendezvous)){
                rendezvousRepository.save(rendezvous);
            return MessagesRendezvous.ADD_RDV_SUCCESS.getMessage();
        }
        return MessagesRendezvous.PATIENT_EXIST.getMessage();
    }
}
