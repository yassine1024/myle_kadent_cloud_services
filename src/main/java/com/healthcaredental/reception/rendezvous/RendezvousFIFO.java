package com.healthcaredental.reception.rendezvous;

import org.springframework.beans.factory.annotation.Autowired;

public class RendezvousStrict implements RendezvousManagement{

    @Autowired
    private RendezvousRepository rendezvousRepository;
    @Override
    public void getListRendezvousManagement() {

    }

    @Override
    public String addRendezvous(Rendezvous rendezvous) {

        if(this.getNbrRendezvous(rendezvous.getDate())>=15){
            return MessagesRendezvous.NBR_RDV_LIMIT.getMessage();
        }
        rendezvousRepository.save(rendezvous);
        return MessagesRendezvous.ADD_RDV_SUCCESS.getMessage();
    }

    private int getNbrRendezvous(String date){
        return rendezvousRepository.getNbrRendezvousByDate(date);
    }
}
