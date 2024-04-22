package com.healthcaredental.reception.rendezvous;

import com.healthcaredental.reception.employee.medecin.Medecin;
import lombok.Data;

import javax.persistence.*;

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
