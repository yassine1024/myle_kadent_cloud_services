package com.healthcaredental.reception.rendezvous;


import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class RendezvousFIFO implements RendezvousManagement{


    @Override
    public void getListRendezvousManagement() {

    }

    @Override
    public String addRendezvous(Rendezvous rendezvous,RendezvousRepository rendezvousRepository) {

        if(this.getNbrRendezvous(rendezvous.getDate(),rendezvousRepository)>=15){
            return MessagesRendezvous.NBR_RDV_LIMIT.getMessage();
        }
        rendezvousRepository.save(rendezvous);
        return MessagesRendezvous.ADD_RDV_SUCCESS.getMessage();
    }

    private int getNbrRendezvous(LocalDate date, RendezvousRepository rendezvousRepository){
        System.out.println(rendezvousRepository.getNbrRendezvousByDate(date));
        return rendezvousRepository.getNbrRendezvousByDate(date);
    }
}
