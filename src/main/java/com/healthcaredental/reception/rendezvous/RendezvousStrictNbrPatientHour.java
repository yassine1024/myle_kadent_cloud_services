package com.healthcaredental.reception.rendezvous;

import org.springframework.stereotype.Component;

@Component
public class RendezvousStrictNbrPatientHour implements RendezvousManagement{
    @Override
    public void getListRendezvousManagement() {

    }

    @Override
    public String addRendezvous(Rendezvous rendezvous, RendezvousRepository rendezvousRepositoryStrict) {

        if(getNbrRendezvous(rendezvous,rendezvousRepositoryStrict)>=3){
            return MessagesRendezvous.NBR_RDV_LIMIT.getMessage();
        }

        rendezvousRepositoryStrict.save(rendezvous);
        return MessagesRendezvous.ADD_RDV_SUCCESS.getMessage();
    }

    private int getNbrRendezvous(Rendezvous rendezvous, RendezvousRepository rendezvousRepositoryStrict){
//        System.out.println(rendezvousRepository.getNbrRendezvousByDate(date));
//        return rendezvousRepositoryStrict.getNbrRendezvousByDateAndHour(rendezvous);
        return -1;
//        return rendezvousRepositoryStrict.getNbrRendezvousByDateAndHour(rendezvous.getDate(),rendezvous.getTime());
    }
}
