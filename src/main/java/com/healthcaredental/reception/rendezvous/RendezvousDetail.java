package com.healthcaredental.reception.rendezvous;

import com.healthcaredental.reception.employee.medecin.Medecin;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class RendezvousDetail {
    @EmbeddedId
    private RendezvousDetailId id;
    @ManyToOne
    private Medecin medecin;
    private String arriveTime;
    private String enterTime;
    private String exitTime;
}
