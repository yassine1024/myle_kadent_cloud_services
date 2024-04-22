package com.healthcaredental.reception.rendezvous;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RendezvousDetailService {

    private final RendezvousDetailRepository rendezvousDetailRepository;

    public Boolean addListConfirmedAppointments(List<Rendezvous> confirmedRendezvous) {

        List<RendezvousDetail> rendezvousDetails = new ArrayList<RendezvousDetail>();

        for (Rendezvous rendezvous : confirmedRendezvous) {
            RendezvousDetail rendezvousDetail = new RendezvousDetail();
            rendezvousDetail.setId(new RendezvousDetailId(rendezvous, rendezvous.getDate()));
            rendezvousDetails.add(rendezvousDetail);
        }

        this.rendezvousDetailRepository.saveAll(rendezvousDetails);
        return true;
    }
}
